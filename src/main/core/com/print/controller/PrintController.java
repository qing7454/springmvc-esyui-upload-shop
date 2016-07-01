package com.print.controller;

import com.code.entity.TableFieldBean;
import com.code.service.ITableInfService;
import com.code.service.impl.TableInfService;
import com.print.entity.BasePrintTemplateEntity;
import com.print.service.PrintService;
import com.sys.util.BeanUtil;
import com.sys.util.DataGrid;
import com.sys.util.DicUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/sys/print")
public class PrintController {
    @Resource
    private PrintService printService;
    @Resource
    private ITableInfService tableInfService;
    @RequestMapping(params = "printList")
    public String printList(DataGrid dataGrid,String _tableName,String preView, HttpServletRequest request){
        Assert.notNull(_tableName);
        Class entityClass=DicUtil.getEntityClass(_tableName);
        Assert.notNull(entityClass);
        if(entityClass!=null){
            Map<String,Object> titleMap=  tableInfService.getTitleMap(_tableName,false,false);
            List<Criterion> criterions= BeanUtil.generateCriterions(entityClass,request,false);
            printService.fillDataGrid(entityClass,criterions,dataGrid);
            List dataList=dataGrid.getDataList();
            List<Map<String,Object>> dataMapList=new ArrayList<>();
            if(dataList!=null&&dataList.size()>0){
                for(Object o:dataList){
//                    dataMapList.add(JacksonDIctUtil.object2MapWithDict(o,_tableName,true));
                }
            }
            request.setAttribute("dataList",dataMapList);
            request.setAttribute("titleMap",titleMap);
            request.setAttribute("preView",preView);
        }
        return "sys/print/printData";
    }

    @RequestMapping(params = "hasRecord")
    @ResponseBody
    public String hasRecord(String _tableName,HttpServletRequest request){
        Assert.notNull(_tableName);
        List<Criterion> list = new ArrayList<Criterion>();
        list.add(Restrictions.eq("mTablename", _tableName));
        List<org.hibernate.criterion.Order> orderList = new ArrayList<Order>();
        List recordList = printService.getDataList(BasePrintTemplateEntity.class, list, orderList);
        List<Map<String,Object>> dataMapList=new ArrayList<>();
        if(recordList!=null && recordList.size()>0){
            for(Object o:recordList){
//                dataMapList.add(JacksonDIctUtil.object2MapWithDict(o,_tableName,true));
            }
        }else {
            return  "0";
        }
        request.setAttribute("recordList",dataMapList);
        return  "1";
    }

    @RequestMapping(params = "compliteModels")
    public String compliteModels(String tableName, HttpServletRequest request){
        request.setAttribute("tableName",tableName);


        return "businesscore/archives_lj/archives_lj_detail";
    }
}
