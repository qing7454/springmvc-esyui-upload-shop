package business.order.controller;

import business.account.entity.AccountEntity;
import business.goods.service.GoodsService;
import business.task.entity.TaskEntity;
import com.sys.constant.Globals;
import business.order.entity.TOrderEntity;
import business.order.service.TOrderService;
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
import java.util.*;

@Controller
@RequestMapping("/business/order")
public class TOrderController {
    @Resource
    private TOrderService tOrderService;
    @Resource
    private ISystemService systemService;
    @Resource
    private GoodsService goodsService;
    /**
    * 转入页面
    * @return
    */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("_modulesLink",ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/order/t_order_list";
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
        List<Criterion> list= BeanUtil.generateCriterions(TOrderEntity.class, request, false);

        tOrderService.fillDataGrid(TOrderEntity.class,list,d);
        return d;
    }
    /**
    * 保存或更新
    * @param bean
    * @return
    */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(TOrderEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=tOrderService.saveReturnId(bean);
                json.setSuccess(id!=null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            TOrderEntity tOrderEntity=tOrderService.getEntity(TOrderEntity.class,bean.getId());
            if(tOrderEntity==null) tOrderEntity=new TOrderEntity();
            BeanUtil.copyNotNull2Bean(bean,tOrderEntity);
            json.setSuccess(tOrderService.update(tOrderEntity));
            logType=Globals.LOG_UPDATE;
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(TOrderEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }

    /**
     * 获取实体bean 返回json
     * @param id
     * @return
     */
    @RequestMapping(params = "get")
    @ResponseBody
    public TOrderEntity get(String id){
        Assert.notNull(id);
        return  tOrderService.getEntity(TOrderEntity.class,id);
    }
    /**
     * 获取实体bean 返回json
     * @param num
     * @return
     */
    @RequestMapping(params = "getNumCheck")
    @ResponseBody
    public SuccessMsg getNumCheck(String num){
        SuccessMsg msg = new SuccessMsg();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("ddnum",num));
        List<TOrderEntity> orders = this.tOrderService.getDataList(TOrderEntity.class,list,null);
        if(orders.size()>0){
            msg.setSuccess(false);
            msg.setMsg("该订单号已存在！");
        }else {
            msg.setSuccess(true);
            msg.setMsg("");
        }
        return  msg;
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
        request.setAttribute("bean",tOrderService.getEntity(TOrderEntity.class,id));
        return "business/order/t_order_detail";
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
        TOrderEntity d=new TOrderEntity();
        d.setId(id);
        j.setSuccess(tOrderService.delete(d));
        if(j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(TOrderEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
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
                if(!tOrderService.delete(new TOrderEntity(ids[i]))){
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(TOrderEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
            }
        }
        return j;
    }

    /**
     * 提交订单
     * @param taskId
     * @param accountId
     * @param level
     * @param idcard
     * @param name
     * @param address
     * @param bankcard
     * @param payment
     * @param orderNum
     * @return
     */
    @RequestMapping(params = "submitOrder")
    @ResponseBody
    public SuccessMsg submitOrder(String taskId,String accountId,String level,
                                  String idcard,String name,String address,
                                  String bankcard,Float payment,String orderNum,HttpServletRequest request){
        SuccessMsg msg = new SuccessMsg();
        HttpSession s=request.getSession();
        SessionUser sessionUser=(SessionUser)s.getAttribute("login_session_user");
        TOrderEntity order = new TOrderEntity();
        TaskEntity task = this.tOrderService.getEntity(TaskEntity.class,taskId);
        AccountEntity account = this.tOrderService.getEntity(AccountEntity.class,accountId);
        account.setLevel(level);
        account.setIdcard(idcard);
        account.setName(name);
        account.setAddress(address);
        this.tOrderService.update(account);        //更新賬號信息
        order.setAccount(account.getAccount());
        order.setAccountid(accountId);
        order.setBankcard(bankcard);
        order.setDdnum(orderNum);
        order.setGoodnum(task.getSku());
        order.setPayment(payment);
        if("PC".equals(task.getSdfs())){
            order.setCommissionXd(this.goodsService.getCommissionBySKU(task.getSku(),0));    //PC下单佣金
        }else if("APP".equals(task.getSdfs())){
            order.setCommissionXd(this.goodsService.getCommissionBySKU(task.getSku(),1));    //APP下单佣金
        }

        order.setXdsj(new Date());
        order.setXdpersonid(sessionUser.getUserId());
        order.setTaskid(taskId);
        order.setShopname(task.getShopname());
        if(this.tOrderService.save(order)){       //保存訂單信息
            //如果提交成功，任務標記為已完成  0:未分配，1：已分配。2：已完成
            task.setTaskstate(2);
            this.tOrderService.update(task);
            msg.setSuccess(true);
            msg.setMsg("提交成功");
        }else {
            msg.setSuccess(false);
            msg.setMsg("提交失败");
        }
        return msg;
    }
}
