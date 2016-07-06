package business.shop.controller;

import com.sys.constant.Globals;
import business.shop.entity.ShopEntity;
import business.shop.service.ShopService;
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
@RequestMapping("/business/shop")
public class ShopController {
    @Resource
    private ShopService shopService;
    @Resource
    private ISystemService systemService;

    /**
    * 转入页面
    * @return
    */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("_modulesLink",ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/shop/shop_list";
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
        List<Criterion> list= BeanUtil.generateCriterions(ShopEntity.class, request, false);

        shopService.fillDataGrid(ShopEntity.class, list, d);
        return d;
    }
    /**
    * 保存或更新
    * @param bean
    * @return
    */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(ShopEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=shopService.saveReturnId(bean);
                json.setSuccess(id!=null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            ShopEntity shopEntity=shopService.getEntity(ShopEntity.class,bean.getId());
            if(shopEntity==null) shopEntity=new ShopEntity();
            BeanUtil.copyNotNull2Bean(bean,shopEntity);
            json.setSuccess(shopService.update(shopEntity));
            logType=Globals.LOG_UPDATE;
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(ShopEntity.class.getSimpleName() + json.getMsg(), logType);
        return json;
    }

    /**
    * 获取实体bean 返回json
    * @param id
    * @return
    */
    @RequestMapping(params = "get")
    @ResponseBody
    public ShopEntity get(String id){
        Assert.notNull(id);
        return  shopService.getEntity(ShopEntity.class,id);
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
        request.setAttribute("bean", shopService.getEntity(ShopEntity.class, id));
        return "business/shop/shop_detail";
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
        ShopEntity d=new ShopEntity();
        d.setId(id);
        j.setSuccess(shopService.delete(d));
        if(j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(ShopEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
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
                if(!shopService.delete(new ShopEntity(ids[i]))){
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(ShopEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
            }
        }
        return j;

    }

    /**
     * 根据店铺代码查看店铺是否存在
     * @param shopCode
     * @return
     */
    @RequestMapping(params = "getCheck")
    @ResponseBody
    public SuccessMsg getCheck(String shopCode){
        SuccessMsg msg = new SuccessMsg();

        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("name",shopCode));
        List<ShopEntity> shops = this.shopService.getDataList(ShopEntity.class,list,null);
        if(shops.size()>0){
            msg.setSuccess(true);
            msg.setMsg("存在");
        }else {
            msg.setSuccess(false);
            msg.setMsg("不存在");
        }
        return msg;
    }
}
