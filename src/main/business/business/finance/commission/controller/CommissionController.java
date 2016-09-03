package business.finance.commission.controller;

import business.finance.commission.entity.CommissionEntity;
import business.finance.commission.service.CommissionService;
import business.order.entity.TOrderEntity;
import business.order.service.TOrderService;
import com.sys.util.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * CommissionController
 * Created by 程洪强 on 2016/8/29.
 */
@Controller
@RequestMapping("/business/commission")
public class CommissionController {

    @Resource
    private CommissionService commissionService;

    @Resource
    private TOrderService tOrderService;

    /**
     * 转入页面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/finance/commission/commission_list";
    }

    /**
     * 获取数据列表
     *
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d, HttpServletRequest request) {
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list = BeanUtil.generateCriterions(TOrderEntity.class, request, false);
        tOrderService.fillDataGrid(TOrderEntity.class, list, d);
        return d;
    }

    /**
     * 获取统计列表
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(params = "dataGrid1")
    @ResponseBody
    public DataGrid dataGrid1(HttpServletRequest request,String shopName,String startDate,String endDate) throws Exception{
        Date date_start = null;
        Date date_end = null;
        if(StringUtils.isNotBlank(startDate)){
            date_start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        }
        if(StringUtils.isNotBlank(endDate)){
            date_end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        }

        Float commissionAmount = 0f;
        DataGrid dataGrid = new DataGrid();
        List<String> shopNames = new ArrayList();
        if(StringUtils.isNotBlank(shopName)){
            shopNames.add(shopName);
        }else {
            shopNames = this.commissionService.getShopsByDate(date_start,date_end);
        }
        List<CommissionEntity> entities = this.commissionService.getCommissionStatistics(shopNames,date_start,date_end);
        for(CommissionEntity commission :entities){
            commissionAmount = commissionAmount+ commission.getAmount();
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("commissionAmount",commissionAmount);
        dataGrid.setDataMap(dataMap);
        dataGrid.setDataList(entities);
        dataGrid.setTotalCount(entities.size());
        dataGrid.setPagesize(entities.size());
        return dataGrid;
    }
}
