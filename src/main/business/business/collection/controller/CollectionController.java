package business.collection.controller;

import business.shop.entity.ShopEntity;
import com.sys.constant.Globals;
import business.collection.entity.CollectionEntity;
import business.collection.service.CollectionService;
import com.sys.entity.SessionUser;
import com.sys.service.SysDepService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import com.sys.service.ISystemService;
import com.sys.util.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/business/collection")
public class CollectionController {
    @Resource
    private CollectionService collectionService;
    @Resource
    private ISystemService systemService;
@Resource
private SysDepService sysDepService;
    /**
    * 转入页面
    * @return
    */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("_modulesLink",ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/collection/collection_list";
    }
    /**
    * 获取数据列表
    * @param
    * @return
    */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d,HttpServletRequest request){
        if(StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list= BeanUtil.generateCriterions(CollectionEntity.class, request, false);


        collectionService.fillDataGrid(CollectionEntity.class, list, d);
        return d;
    }
    /**
    * 保存或更新
    * @param bean
    * @return
    */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(CollectionEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=collectionService.saveReturnId(bean);
                json.setSuccess(id!=null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            CollectionEntity collectionEntity=collectionService.getEntity(CollectionEntity.class,bean.getId());
            if(collectionEntity==null) collectionEntity=new CollectionEntity();
            BeanUtil.copyNotNull2Bean(bean,collectionEntity);
            json.setSuccess(collectionService.update(collectionEntity));
            logType=Globals.LOG_UPDATE;
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(CollectionEntity.class.getSimpleName() + json.getMsg(), logType);
        return json;
    }

    /**
    * 获取实体bean 返回json
    * @param id
    * @return
    */
    @RequestMapping(params = "get")
    @ResponseBody
    public CollectionEntity get(String id){
        Assert.notNull(id);
        return  collectionService.getEntity(CollectionEntity.class,id);
    }

    /**
    * 获取详情
    * @param id
    * @param request
    * @return
    */
    @RequestMapping(params = "detail")
    public String detail(String id,HttpServletRequest request){
        Assert.notNull(id);
        request.setAttribute("bean", collectionService.getEntity(CollectionEntity.class, id));
        return "business/collection/collection_detail";
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @RequestMapping(params = "del")
    @ResponseBody
    public SuccessMsg del(String id){
        SuccessMsg j=new SuccessMsg();
        CollectionEntity d=new CollectionEntity();
        d.setId(id);
        j.setSuccess(collectionService.delete(d));
        if(j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(CollectionEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
        return j;
    }

    /**
    * 批量删除
    * @param ids
    * @return
    */
    @RequestMapping(params = "muldel")
    @ResponseBody
    public SuccessMsg mulDel(String[] ids){
        SuccessMsg j=new SuccessMsg();
        if (ids != null) {
            j.setSuccess(true);
            j.setMsg("成功删除"+ids.length+"条记录");
            for(int i=0;i<ids.length;i++){
                if(!collectionService.delete(new CollectionEntity(ids[i]))){
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(CollectionEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
            }
        }
        return j;

    }

    /**
     * 保存或更新
     * @param bean
     * @return
     */
    @RequestMapping(params = "collection")
    @ResponseBody
    public SuccessMsg collection(CollectionEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=collectionService.saveReturnId(bean);
            //保存成功，减去商户对应的欠款
            List<Criterion> list = new ArrayList<>();
            //店铺的name 和收款信息的店铺编号对应
            list.add(Restrictions.eq("name",bean.getShopnum()));
            List<ShopEntity> shops = this.collectionService.getDataList(ShopEntity.class,list,null);
            if(shops.size()>0){
                ShopEntity shop = shops.get(0);
                if(bean.getCollectionType().equals("01")){  //货款
                    shop.setPayment(shop.getPayment()-bean.getCollectionMoney());
                    this.collectionService.update(shop);
                }else if(bean.getCollectionType().equals("02")){  //佣金
                    shop.setCommission(shop.getCommission()-bean.getCollectionMoney());
                    this.collectionService.update(shop);
                }
            }
            json.setSuccess(id != null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(CollectionEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }
}
