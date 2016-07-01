package business.setting.controller;

import com.sys.constant.Globals;
import business.setting.entity.SettingEntity;
import business.setting.service.SettingService;
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
@RequestMapping("/business/setting")
public class SettingController {
    @Resource
    private SettingService settingService;
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
        List<Criterion> list = new ArrayList<>();
        List<SettingEntity> settings = this.settingService.getDataList(SettingEntity.class,list,null);
        if(settings.size() > 0){
            request.setAttribute("id",settings.get(0).getId());
        }
        return "business/setting/setting_update";
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
        List<Criterion> list= BeanUtil.generateCriterions(SettingEntity.class, request, false);

        if ("2".equals(request.getParameter("cz"))) {
        list.add(Restrictions.or(Restrictions.eq("syszt", 0), Restrictions.eq("syszt", 2)));
        } else {
        list.add(Restrictions.eq("syszt", 0));
        }

    // 权限有两种可能，材料级别，
    // 市级，【部门号0536，潍坊市】，可以看到所有的
    // 市直级，【部门号0536，潍坊市】，可以看到所有的
    // 局级，【部门号0536001，临朐县】，可以看到临朐本部门和子部门所有的
    // 区户籍管理，【部门号：053600101：临朐县户籍管理】可以看到临朐户籍部门和子部门所有的
    // 区—管理中心，【部门号：05360010101：临朐县户籍管理—管理中心】可以看到临朐户籍部门，户籍科领导所属。
    // 区—管理中心-户籍科，【部门号：05360010101001：临朐县户籍管理—管理中心—户籍科】只能看自己的，业务员。
    // 档案级别：凡是能看到档案的人，均具有，档案管理的角色权限，所以最底层不同于材料级别，可以看到本部门，其他不变
    HttpSession s=request.getSession();
    SessionUser sessionUser=(SessionUser)s.getAttribute("login_session_user");
    if(sessionUser.getDep().getDepLogoId().length()>=13)
    {
    list.add(Restrictions.eq("_createUserId", sessionUser.getUserId()));
    }
    else{
    List<Long> depList= sysDepService.getChildrenId((sessionUser.getDep().getId()));
        list.add(Restrictions.in("depIdIdentify", depList));
        }


        settingService.fillDataGrid(SettingEntity.class,list,d);
        return d;
    }
    /**
    * 保存或更新
    * @param bean
    * @return
    */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(SettingEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=settingService.saveReturnId(bean);
                json.setSuccess(id!=null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            SettingEntity settingEntity=settingService.getEntity(SettingEntity.class,bean.getId());
            if(settingEntity==null) settingEntity=new SettingEntity();
            BeanUtil.copyNotNull2Bean(bean,settingEntity);
            json.setSuccess(settingService.update(settingEntity));
            logType=Globals.LOG_UPDATE;
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(SettingEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }

    /**
    * 获取实体bean 返回json
    * @param id
    * @return
    */
    @RequestMapping(params = "get")
    @ResponseBody
    public SettingEntity get(String id){
        Assert.notNull(id);
        return  settingService.getEntity(SettingEntity.class,id);
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
        request.setAttribute("bean",settingService.getEntity(SettingEntity.class,id));
        return "business/setting/setting_detail";
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
        SettingEntity d=new SettingEntity();
        d.setId(id);
        j.setSuccess(settingService.delete(d));
        if(j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(SettingEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
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
                if(!settingService.delete(new SettingEntity(ids[i]))){
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(SettingEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
            }
        }
        return j;

    }
}
