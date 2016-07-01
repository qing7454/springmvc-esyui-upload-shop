/*
 *  SupervisionController
 *  Created on 2016/6/17 10:39
 *  
 *  版本       修改时间          作者        修改内容 
 *  V0.1       2016/6/17          程洪强      初始版本 
 *  V0.2       
 *  V0.3       
 *  V0.4       
 *  V0.5       
 *  Copyright (c) 2016 SK项目组 版权所有 
 *  SK project team All Rights Reserved. 
 */
package business.task.controller;

import business.task.entity.SupervisionEntity;
import business.task.entity.TaskEntity;
import business.task.service.TaskService;
import com.sys.entity.SysUserEntity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * SupervisionController
 * Created by 程洪强 on 2016/6/17.
 */
@Controller
@SuppressWarnings("/business/supervision")
public class SupervisionController {

    @Resource
    private TaskService taskService;

    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) throws Exception{
        List<SupervisionEntity> supervisions= new ArrayList<>();
        List<SupervisionEntity> supervisions1= new ArrayList<>();
        SupervisionEntity supervision = new SupervisionEntity();
        SupervisionEntity supervision1 = new SupervisionEntity();
        supervision.setRealName("总计");
        supervision1.setRealName("总计");
        int complete = 0;
        int unComplete = 0;
        int taskCount = 0;
        int complete1 = 0;
        int unComplete1 = 0;
        int taskCount1 = 0;
        //获取所有刷手账号
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("userType",2));   //刷手账号，字典中定义
        List<SysUserEntity> users = this.taskService.getDataList(SysUserEntity.class,list,null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = sdf.format(cal.getTime());
        String dateStr = sdf.format(new Date());
        try{
            Date assignDate = sdf.parse(dateStr);
            Date yesterDate = sdf.parse(yesterday);
            for(SysUserEntity user : users){
                //当天
                List<Criterion> list1 = new ArrayList<>();
                list1.add(Restrictions.eq("ownerid",user.getId()));
                list1.add(Restrictions.eq("assigndate",assignDate));
                List<TaskEntity> tasks = this.taskService.getDataList(TaskEntity.class, list1, null);

                SupervisionEntity entity = countTask(user,tasks);
                taskCount = taskCount + tasks.size();
                complete = complete+entity.getComplete();
                unComplete = unComplete + entity.getUnComplete();
                supervisions.add(entity);
                //前一天
                List<Criterion> list2 = new ArrayList<>();
                list2.add(Restrictions.eq("ownerid",user.getId()));
                list2.add(Restrictions.eq("assigndate",yesterDate));
                List<TaskEntity> tasks1 = this.taskService.getDataList(TaskEntity.class, list2, null);

                SupervisionEntity entity1 = countTask(user,tasks1);
                taskCount1 = taskCount1 + tasks1.size();
                complete1 = complete1+entity1.getComplete();
                unComplete1 = unComplete1 + entity1.getUnComplete();
                supervisions1.add(entity1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //当天
        supervision.setComplete(complete);
        supervision.setUnComplete(unComplete);
        supervision.setTaskCount(taskCount);
        if(taskCount == 0){
            supervision.setPercent(0f);
        }else {
            float a = (complete*1.0f)/(taskCount*1.0f);
            supervision.setPercent(Math.round(a*100)*1.0f);
        }
        //前一天
        supervision1.setComplete(complete1);
        supervision1.setUnComplete(unComplete1);
        supervision1.setTaskCount(taskCount1);
        if(taskCount1 == 0){
            supervision1.setPercent(0f);
        }else {
            float a = (complete1*1.0f)/(taskCount1*1.0f);

            supervision1.setPercent(Math.round(a*100)*1.0f);
        }

        request.setAttribute("supervision",supervision);
        request.setAttribute("datas",supervisions);
        request.setAttribute("supervision1",supervision1);
        request.setAttribute("datas1",supervisions1);
        return "business/task/supervision";
    }


    private SupervisionEntity countTask(SysUserEntity user ,List<TaskEntity> tasks){
        SupervisionEntity supervision = new SupervisionEntity();
        supervision.setUserId(user.getId());
        supervision.setRealName(user.getRealname());
        supervision.setTaskCount(tasks.size());
        int complete = 0;
        int unComplete = 0;
        for(TaskEntity task : tasks){
            if(task.getTaskstate()==1){   //已分配，未完成
                unComplete++;
            }else if(task.getTaskstate()==2){  //已完成
                complete++;
            }
        }
        supervision.setComplete(complete);
        supervision.setUnComplete(unComplete);
        if(tasks.size() == 0){
            supervision.setPercent(0f);
        }else {
            float a = (complete*1.0f)/(tasks.size()*1.0f);
            supervision.setPercent(Math.round(a*100)*1.0f);
        }
        return supervision;
    }

}
