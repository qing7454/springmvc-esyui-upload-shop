package com.print.controller;

import com.google.gson.reflect.TypeToken;
import com.print.entity.BasePrintTemplateEntity;
import com.print.service.BasePrintTemplateService;
import com.sys.constant.Globals;
import com.sys.service.ISystemService;
import com.sys.util.BeanUtil;
import com.sys.util.DataGrid;
import com.sys.util.JsonUtil;
import com.sys.util.SuccessMsg;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/printTemplate")
public class BasePrintTemplateController {
    @Resource
    private BasePrintTemplateService basePrintTemplateService;
    @Resource
    private ISystemService systemService;
    /**
    * 转入页面
    * @return
    */
    @RequestMapping(params = "base_print_template")
    public String base_print_template(){
    return "sys/print/base_print_template_list";
    }
    /**
    * 获取数据列表
    * @param
    * @return
    */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d,HttpServletRequest request){
        List<Criterion> list= BeanUtil.generateCriterions(BasePrintTemplateEntity.class, request, false);
        basePrintTemplateService.fillDataGrid(BasePrintTemplateEntity.class,list,d);
        return d;
    }
    /**
    * 保存或更新
    * @param bean
    * @return
    */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(BasePrintTemplateEntity bean){
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        Map<String,Object> dataMap=new HashMap<>();
        if(StringUtils.isBlank(bean.getId())){
            bean.setId(null);
            Serializable id=basePrintTemplateService.saveReturnId(bean);
                json.setSuccess(id!=null);
            try {
                BeanUtils.setProperty(bean,"id",id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            BasePrintTemplateEntity basePrintTemplateEntity=basePrintTemplateService.getEntity(BasePrintTemplateEntity.class,bean.getId());
            if(basePrintTemplateEntity!=null) basePrintTemplateEntity=new BasePrintTemplateEntity();
            BeanUtil.copyNotNull2Bean(bean,basePrintTemplateEntity);
            json.setSuccess(basePrintTemplateService.update(basePrintTemplateEntity));
            logType=Globals.LOG_UPDATE;
        }
        dataMap.put("bean",bean);
        json.setDataMap(dataMap);
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(BasePrintTemplateEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }
    /**
     * 保存或更新
     * @param jsonData
     * @return
     */
    @RequestMapping(params = "jsonSave")
    @ResponseBody
    public SuccessMsg jsonSave(String jsonData){
        Assert.notNull(jsonData);
        List<BasePrintTemplateEntity> entitys= JsonUtil.getGson().fromJson(jsonData,new TypeToken<List<BasePrintTemplateEntity>>(){}.getType());
        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_INSERT;
        if(entitys!=null&&entitys.size()>0){
            json.setSuccess(basePrintTemplateService.bulkSave(entitys.get(0).getMTablename(), entitys));
        }
        if(json.isSuccess()) json.setMsg("保存成功!");
        else  json.setMsg("保存失败！");
        systemService.addLog(BasePrintTemplateEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }
    /**
     * 删除模板
     * @param tableName
     * @return
     */
    @RequestMapping(params = "delTemplate")
    @ResponseBody
    public SuccessMsg delTemplate(String tableName,String templateName){

        SuccessMsg json=new SuccessMsg();
        String logType=Globals.LOG_DEL;
        json.setSuccess(basePrintTemplateService.delTemplate(tableName,templateName));
        if(json.isSuccess()) json.setMsg("删除成功!");
        else  json.setMsg("删除失败！");
        systemService.addLog(BasePrintTemplateEntity.class.getSimpleName()+ json.getMsg(), logType);
        return json;
    }
    /**
     * 通过表名获取模板名称
     * @param tableName
     * @return
     */
    @RequestMapping(params = "templates")
    @ResponseBody
    public List<String> getTemplates(String tableName){
        return basePrintTemplateService.getTemplateNamesByTableName(tableName);
    }
    /**
     * 通过表名和模板名获取模板
     * @param tableName
     * @return
     */
    @RequestMapping(params = "getTemplate")
    @ResponseBody
    public List<BasePrintTemplateEntity> getTableTemplate(String tableName,String templateName){
        return basePrintTemplateService.getTemplateByTableNameAndTemplateName(tableName,templateName);
    }
    /**
    * 获取实体bean 返回json
    * @param id
    * @return
    */
    @RequestMapping(params = "get")
    @ResponseBody
    public BasePrintTemplateEntity get(String id){
        Assert.notNull(id);
        return  basePrintTemplateService.getEntity(BasePrintTemplateEntity.class,id);
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
        request.setAttribute("bean",basePrintTemplateService.getEntity(BasePrintTemplateEntity.class,id));
        return "sys/print/base_print_template_detail";
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
        BasePrintTemplateEntity d=new BasePrintTemplateEntity();
        d.setId(id);
        j.setSuccess(basePrintTemplateService.delete(d));
        if(j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(BasePrintTemplateEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
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
                if(!basePrintTemplateService.delete(new BasePrintTemplateEntity(ids[i]))){
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(BasePrintTemplateEntity.class.getSimpleName()+j.getMsg(),Globals.LOG_DEL);
            }
        }
        return j;

    }
}
