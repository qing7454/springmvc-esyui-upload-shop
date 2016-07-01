package business.task.controller;

import business.account.entity.AccountEntity;
import business.order.entity.TOrderEntity;
import com.sys.constant.Globals;
import business.task.entity.TaskEntity;
import business.task.service.TaskService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/business/task")
public class TaskController {
    @Resource
    private TaskService taskService;
    @Resource
    private ISystemService systemService;

    /**
     * 转入任务管理页面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/task/task_list";
    }
    /**
     * 转入任务分配页面
     *
     * @return
     */
    @RequestMapping(params = "toList2")
    public String toList2(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/task/task_list2";
    }
    /**
     * 转入我的任务页面
     *
     * @return
     */
    @RequestMapping(params = "toList3")
    public String toList3(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/task/task_list3";
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
        List<Criterion> list = BeanUtil.generateCriterions(TaskEntity.class, request, false);

        taskService.fillDataGrid(TaskEntity.class, list, d);
        return d;
    }
    /**
     * 获取任务分配数据列表
     *
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid2")
    @ResponseBody
    public DataGrid datagrid2(DataGrid d, HttpServletRequest request) {
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list = BeanUtil.generateCriterions(TaskEntity.class, request, false);
        list.add(Restrictions.eq("taskstate",0));
        taskService.fillDataGrid(TaskEntity.class, list, d);
        return d;
    }
    /**
     * 获取我的任务数据列表
     *
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid3")
    @ResponseBody
    public DataGrid datagrid3(DataGrid d, HttpServletRequest request) {
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        HttpSession s=request.getSession();
        SessionUser sessionUser=(SessionUser)s.getAttribute("login_session_user");
        String userId = sessionUser.getUserId();
        List<Criterion> list = BeanUtil.generateCriterions(TaskEntity.class, request, false);
        list.add(Restrictions.eq("ownerid", userId));
        taskService.fillDataGrid(TaskEntity.class, list, d);
        return d;
    }

    /**
     * 导入任务
     * @param taskFile
     * @return
     */
    @RequestMapping(params = "taskImport")
    @ResponseBody
    public SuccessMsg taskImport(MultipartFile taskFile){
        SuccessMsg msg = new SuccessMsg();
        if(!this.taskService.importTask(taskFile)){
            msg.setSuccess(false);
            msg.setMsg("导入失败");
        }else {
            msg.setSuccess(true);
            msg.setMsg("导入成功");
        }

        return msg;
    }

    /**
     * 保存或更新
     *
     * @param bean
     * @return
     */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(TaskEntity bean) {
        SuccessMsg json = new SuccessMsg();
        String logType = Globals.LOG_INSERT;
        Map<String, Object> dataMap = new HashMap<>();
        if (StringUtils.isBlank(bean.getId())) {
            bean.setId(null);
            Serializable id = taskService.saveReturnId(bean);
            json.setSuccess(id != null);
            try {
                BeanUtils.setProperty(bean, "id", id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            TaskEntity taskEntity = taskService.getEntity(TaskEntity.class, bean.getId());
            if (taskEntity == null) taskEntity = new TaskEntity();
            BeanUtil.copyNotNull2Bean(bean, taskEntity);
            json.setSuccess(taskService.update(taskEntity));
            logType = Globals.LOG_UPDATE;
        }
        dataMap.put("bean", bean);
        json.setDataMap(dataMap);
        if (json.isSuccess()) json.setMsg("保存成功!");
        else json.setMsg("保存失败！");
        systemService.addLog(TaskEntity.class.getSimpleName() + json.getMsg(), logType);
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
    public TaskEntity get(String id) {
        Assert.notNull(id);
        return taskService.getEntity(TaskEntity.class, id);
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
        request.setAttribute("bean", taskService.getEntity(TaskEntity.class, id));
        return "business/task/task_detail";
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
        TaskEntity d = new TaskEntity();
        d.setId(id);
        j.setSuccess(taskService.delete(d));
        if (j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(TaskEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
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
                if (!taskService.delete(new TaskEntity(ids[i]))) {
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(TaskEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
            }
        }
        return j;

    }

    /**
     * 分配任务
     * @param taskIds
     * @param userId
     * @return
     */
    @RequestMapping(params = "assignTask")
    @ResponseBody
    public SuccessMsg assignTask(String[] taskIds,String userId,String userName){
        SuccessMsg msg = new SuccessMsg();
        if (taskIds != null) {
            msg.setSuccess(true);
            msg.setMsg("成功分配" + taskIds.length + "条任务");
            for (int i = 0; i < taskIds.length; i++) {
                TaskEntity task = this.taskService.getEntity(TaskEntity.class,taskIds[i]);
                task.setOwnerid(userId);
                task.setTaskowner(userName);
                task.setTaskstate(1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(new Date());
                try{
                    task.setAssigndate(sdf.parse(dateStr));
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (!taskService.update(task)) {
                    msg.setSuccess(false);
                    msg.setMsg("分配失败!");
                }
                systemService.addLog(TaskEntity.class.getSimpleName() + msg.getMsg(), Globals.LOG_DEL);
            }
        }
        return msg;
    }

    /**
     * 跳转到使用刷单界面
     * @param taskId
     * @param accountId
     * @param request
     * @return
     */
    @RequestMapping(params = "toUserAccount")
    public String toUserAccount(String taskId,String accountId,HttpServletRequest request){
        TaskEntity task = this.taskService.getEntity(TaskEntity.class,taskId);
        AccountEntity account = this.taskService.getEntity(AccountEntity.class, accountId);
        request.setAttribute("task", task);
        request.setAttribute("account",account);

        return "business/task/task_execute_detail1";
    }

    /**
     * 跳转到收货界面
     * @param taskId
     * @param request
     * @return
     */
    @RequestMapping(params = "toReceive")
    public String toReceive(String taskId,HttpServletRequest request){
        TaskEntity task = this.taskService.getEntity(TaskEntity.class, taskId);
        String orderNum = task.getOrdernun();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("ddnum", orderNum));
        List<TOrderEntity> orders = this.taskService.getDataList(TOrderEntity.class,list,null);

        if(orders != null && orders.size() > 0){
            TOrderEntity order = orders.get(0);
            AccountEntity account = this.taskService.getEntity(AccountEntity.class,order.getAccountid());
            request.setAttribute("account",account);
            request.setAttribute("task",task);
        }else {
            request.setAttribute("message","订单不存在");
        }
        if("02".equals(task.getTasktype())){
            return "business/task/task_execute_detail2";
        }else if("03".equals(task.getTasktype())){
            return "business/task/task_execute_detail3";
        }else if("04".equals(task.getTasktype())){
            return "business/task/task_execute_detail4";
        }else {
            return "business/task/task_execute_detail2";
        }

    }

    /**
     * 完成任务
     * @param taskId
     * @return
     */
    @RequestMapping(params = "completeTask")
    @ResponseBody
    public SuccessMsg completeTask(String taskId,HttpServletRequest request) throws Exception{
        SuccessMsg msg = new SuccessMsg();
        TaskEntity entity = this.taskService.getEntity(TaskEntity.class,taskId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        entity.setCompletedate(sdf.parse(dateStr));
        entity.setTaskstate(2);//标记为已完成
        this.taskService.update(entity);
        HttpSession s=request.getSession();
        SessionUser sessionUser=(SessionUser)s.getAttribute("login_session_user");
        String userId = sessionUser.getUserId();
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("ddnum", entity.getOrdernun()));
        List<TOrderEntity> orders = this.taskService.getDataList(TOrderEntity.class,list,null);
        if(orders != null && orders.size() > 0){
            TOrderEntity order = orders.get(0);
            if("02".equals(entity.getTasktype())){    //收货且评价任务
                order.setDjstate(2);//已评价
                order.setShdate(new Date());
                order.setShpersionid(userId);
                order.setPjdate(new Date());
                order.setPjpsersionid(userId);

            }else if("03".equals(entity.getTasktype())){    //收货任务
                order.setDjstate(1);//已收货
                order.setShdate(new Date());
                order.setShpersionid(userId);

            }else if("04".equals(entity.getTasktype())){
                order.setDjstate(2);//已评价
                order.setPjpsersionid(userId);
                order.setPjdate(new Date());
            }
            this.taskService.update(order);
        }
        msg.setSuccess(true);
        msg.setMsg("提交成功");
        return msg;
    }
}
