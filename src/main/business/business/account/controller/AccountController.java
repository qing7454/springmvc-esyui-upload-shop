package business.account.controller;

import business.bankcard.service.BankcardService;
import business.order.entity.TOrderEntity;
import business.setting.entity.SettingEntity;
import business.task.entity.TaskEntity;
import com.sys.constant.Globals;
import business.account.entity.AccountEntity;
import business.account.service.AccountService;
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
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/business/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private ISystemService systemService;
    @Resource
    private BankcardService bankcardService;

    /**
     * 转入页面正常账号界面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/account/account_list";
    }
    /**
     * 转入页面异常账号界面
     *
     * @return
     */
    @RequestMapping(params = "toList2")
    public String toList2(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/account/account_list2";
    }/**
     * 转入页面异常账号界面
     *
     * @return
     */
    @RequestMapping(params = "toList3")
    public String toList3(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/account/account_list3";
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
        List<Criterion> list = BeanUtil.generateCriterions(AccountEntity.class, request, false);
        accountService.fillDataGrid(AccountEntity.class, list, d);
        return d;
    }

    /**
     * 获取任务可用账号
     * @param d
     * @param request
     * @return
     */
    @RequestMapping(params = "getTaskAccount")
    @ResponseBody
    public DataGrid getTaskAccount(DataGrid d,HttpServletRequest request,String taskId){
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list = BeanUtil.generateCriterions(AccountEntity.class, request, false);
        List<SettingEntity> settings = this.accountService.getDataList(SettingEntity.class,new ArrayList<Criterion>(),null);
        SettingEntity setting = new SettingEntity();
        if(settings != null && settings.size()>0){
            setting = settings.get(0);
        }
        TaskEntity task = this.accountService.getEntity(TaskEntity.class,taskId);
        List<AccountEntity> accounts = this.accountService.getDataList(AccountEntity.class,list,null);
        List<AccountEntity> accountList = new ArrayList<>();
        for(AccountEntity account :accounts){

            if(isOverTdplq(account.getId(),setting.getTdplq(),task.getShopname())){                 //同店疲劳期
                continue;
            }else if(isOverSukPlq(account.getId(),task.getSku(),setting.getTskuplq())){  //同SKU疲劳期
                continue;
            }else if (isOverMaxNum(account.getId(),setting.getGmjs())){    //月最多购买件数
                continue;
            }else if(isOverMaxSum(account.getId(),setting.getGmje())){     //月最多购买金额
                continue;
            }else {
                accountList.add(account);
            }
        }
        d.setDataList(accountList);
        d.setTotalCount(accountList.size());
        d.setPageNum(accountList.size());
        return d;
    }


    /**
     * 保存或更新
     *
     * @param bean
     * @return
     */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(AccountEntity bean) {
        SuccessMsg json = new SuccessMsg();
        String logType = Globals.LOG_INSERT;
        Map<String, Object> dataMap = new HashMap<>();
        if (StringUtils.isBlank(bean.getId())) {
            bean.setId(null);
            bean.setAccountstate("01"); //正常账号
            Serializable id = accountService.saveReturnId(bean);
            json.setSuccess(id != null);
            try {
                BeanUtils.setProperty(bean, "id", id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            AccountEntity accountEntity = accountService.getEntity(AccountEntity.class, bean.getId());
            if (accountEntity == null) accountEntity = new AccountEntity();
            BeanUtil.copyNotNull2Bean(bean, accountEntity);
            json.setSuccess(accountService.update(accountEntity));
            logType = Globals.LOG_UPDATE;
        }
        dataMap.put("bean", bean);
        json.setDataMap(dataMap);
        if (json.isSuccess()) json.setMsg("保存成功!");
        else json.setMsg("保存失败！");
        systemService.addLog(AccountEntity.class.getSimpleName() + json.getMsg(), logType);
        return json;
    }

    /**
     * 获取实体bean 返回json
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "get")
    @ResponseBody
    public AccountEntity get(String id) {
        Assert.notNull(id);
        return accountService.getEntity(AccountEntity.class, id);
    }

    /**
     * 获取详情
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(params = "detail")
    public String detail(String id, HttpServletRequest request) {
        Assert.notNull(id);
        request.setAttribute("bean", accountService.getEntity(AccountEntity.class, id));
        return "business/account/account_detail";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public SuccessMsg del(String id) {
        SuccessMsg j = new SuccessMsg();
        AccountEntity entity = accountService.getEntity(AccountEntity.class, id);
        //标记为以删除
        entity.setAccountstate("03");
        j.setSuccess(accountService.update(entity));
        if (j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
        return j;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "muldel")
    @ResponseBody
    public SuccessMsg mulDel(String[] ids) {
        SuccessMsg j = new SuccessMsg();
        if (ids != null) {
            j.setSuccess(true);
            j.setMsg("成功删除" + ids.length + "条记录");
            for (int i = 0; i < ids.length; i++) {
                AccountEntity entity = accountService.getEntity(AccountEntity.class, ids[i]);
                //标记为以删除
                entity.setAccountstate("03");
                if (!accountService.delete(entity)) {
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
            }
        }
        return j;

    }
    /**
     * 标记异常
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "markException")
    @ResponseBody
    public SuccessMsg markException(String id,String reason) {
        SuccessMsg j = new SuccessMsg();
        if (id != null) {
            j.setSuccess(true);
            j.setMsg("成功标记");

            AccountEntity entity = accountService.getEntity(AccountEntity.class, id);
            entity.setAccountstate("02");
            entity.setException(reason);
            if (!accountService.update(entity)) {
                j.setSuccess(false);
                j.setMsg("标记失败!");
            }
            systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);

        }
        return j;

    }
    /**
     * 标记删除
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "markDelete")
    @ResponseBody
    public SuccessMsg markDelete(String id,String reason) {
        SuccessMsg j = new SuccessMsg();
        if (id != null) {
            j.setSuccess(true);
            j.setMsg("成功删除");

            AccountEntity entity = accountService.getEntity(AccountEntity.class, id);
            entity.setAccountstate("03"); //删除标记
            entity.setDeletereason(reason);
            if (!accountService.update(entity)) {
                j.setSuccess(false);
                j.setMsg("删除失败!");
            }
            systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);

        }
        return j;

    }
    /**
     * 解除异常
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "removeException")
    @ResponseBody
    public SuccessMsg removeException(String id) {
        SuccessMsg j = new SuccessMsg();
        if (id != null) {
            j.setSuccess(true);
            j.setMsg("成功解除");

            AccountEntity entity = accountService.getEntity(AccountEntity.class, id);
            entity.setAccountstate("01");  //正常账号
            if (!accountService.update(entity)) {
                j.setSuccess(false);
                j.setMsg("解除失败!");
            }
            systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);

        }
        return j;

    }
    /**
     * 解除异常
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "removeDelete")
    @ResponseBody
    public SuccessMsg removeDelete(String id) {
        SuccessMsg j = new SuccessMsg();
        if (id != null) {
            j.setSuccess(true);
            j.setMsg("成功解除");

            AccountEntity entity = accountService.getEntity(AccountEntity.class, id);
            entity.setAccountstate("02");  //异常账号
            if (!accountService.update(entity)) {
                j.setSuccess(false);
                j.setMsg("解除失败!");
            }
            systemService.addLog(AccountEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);

        }
        return j;

    }

    /**
     * 判断是否超过同店疲劳期
     * @param accountid
     * @param tdplq
     * @return
     */
    public boolean isOverTdplq(String accountid,int tdplq,String shopName){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -tdplq);
        Date date = calendar.getTime();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("accountid", accountid));
        list.add(Restrictions.ge("_createDate", date));
        list.add(Restrictions.ge("shopname",shopName));
        List<TOrderEntity> orders = this.accountService.getDataList(TOrderEntity.class,list,null);
        if(orders != null && orders.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 判断是否超过同SKU疲劳期
     * @param goodId
     * @param tsukplq
     * @return
     */
    public boolean isOverSukPlq(String accountid,String goodId,int tsukplq ){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -tsukplq);
        Date date = calendar.getTime();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("accountid", accountid));
        list.add(Restrictions.eq("goodid", goodId));
        list.add(Restrictions.ge("_createDate", date));
        List<TOrderEntity> orders = this.accountService.getDataList(TOrderEntity.class,list,null);
        if(orders != null && orders.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 判断是否超过月最大购买件数
     * @param accountid
     * @param maxNum
     * @return
     */
    public boolean isOverMaxNum(String accountid,int maxNum){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        Date date = calendar.getTime();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("accountid", accountid));
        list.add(Restrictions.ge("_createDate", date));
        List<TOrderEntity> orders = this.accountService.getDataList(TOrderEntity.class,list,null);
        if(orders != null){
            if(orders.size()>maxNum){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否超过月最大购买金额
     * @param accountid
     * @param maxSum
     * @return
     */
    public boolean isOverMaxSum(String accountid,float maxSum){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        Date date = calendar.getTime();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("accountid", accountid));
        list.add(Restrictions.ge("_createDate", date));
        List<TOrderEntity> orders = this.accountService.getDataList(TOrderEntity.class,list,null);
        if(orders != null){
            float sum = 0f;
            for(TOrderEntity order :orders){
                sum += order.getPayment();
            }
            if(sum > maxSum){
                return true;
            }
        }
        return false;
    }
}
