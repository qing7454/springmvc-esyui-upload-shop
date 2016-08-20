/*
 *  AnalyzeController
 * ?Created?on?2016/6/24 14:41
 * ?
 * ?版本???????修改时间??????????作者??????  修改内容?
 * ?V0.1????   2016/6/24??????    程洪强???  ?初始版本?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK项目组?版权所有?
 * ?SK project team?All?Rights?Reserved.?
 */
package business.analyze.controller;

import business.account.entity.AccountEntity;
import business.analyze.entity.AnalyzeEntity;
import business.task.entity.TaskEntity;
import com.sys.entity.SysUserEntity;
import com.sys.service.SysUserService;
import com.sys.util.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AnalyzeController
 * Created by 程洪强 on 2016/6/24.
 */
@Controller
@RequestMapping("/business/analyze")
public class AnalyzeController {

    @Resource
    private SysUserService sysUserService;
    /**
     * 转入界面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/analyze/analyze_list";
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
        String assign = request.getParameter("assign");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        //需要查询的人
        List<SysUserEntity> users = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(new Date());
        //查询的开始时间
        Date startDate = null;
        //查询的截止时间
        Date endDate = null;
        if(StringUtils.isNotBlank(assign)){    //不为空表示查询某个人的任务信息
            String ownerid = assign;
            SysUserEntity user = this.sysUserService.getEntity(SysUserEntity.class,ownerid);
            users.add(user);
        }else {
            //刷手列表
            users = this.sysUserService.getUsersByType(2);
        }
        try {
            if(StringUtils.isNotBlank(startTime)){
                startDate = sdf.parse(startTime);
            }else {
                startDate = sdf.parse(nowDate);
            }

            if(StringUtils.isNotBlank(endTime)){
                endDate = sdf.parse(endTime);
            }else {
                endDate = sdf.parse(nowDate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //数据列表
        List<AnalyzeEntity> dataList = getAnalyzeData(users,startDate,endDate);

        d.setDataList(dataList);
        d.setPageNum(1);
        d.setPagesize(dataList.size());
        d.setTotalCount(dataList.size());
        return d;
    }

    /**
     * 分析数据
     * @param users
     * @param startDate
     * @param endDate
     * @return
     */
    private List<AnalyzeEntity> getAnalyzeData(List<SysUserEntity> users,Date startDate,Date endDate){
        List<AnalyzeEntity> dataList = new ArrayList<>();
        for (SysUserEntity user :users){
            AnalyzeEntity entity = new AnalyzeEntity();
            List<Criterion> list = new ArrayList<>();
            list.add(Restrictions.eq("ownerid", user.getId()));
//            list.add(Restrictions.ge("completedate", startDate));
//            list.add(Restrictions.le("completedate", endDate));
            list.add(Restrictions.between("completedate",startDate,endDate));
            List<TaskEntity> tasks = this.sysUserService.getDataList(TaskEntity.class,list,null);
            entity.setUserId(user.getId());
            entity.setUserName(user.getRealname());
            //------------------------------统计任务完成数-----------------------------------------
            int sdCount = 0;            //下单总数
            int shAndpjCount = 0;       //收货且评价数量
            int shCount = 0;            //收货数量
            int pjCount = 0;            //评价数量
            for (TaskEntity task :tasks){
                if("01".equals(task.getTasktype())){
                    sdCount ++;
                    continue;
                }else if("02".equals(task.getTasktype())){
                    shAndpjCount ++;
                    continue;
                }else if("03".equals(task.getTasktype())){
                    shCount ++;
                    continue;
                }else if("04".equals(task.getTasktype())){
                    pjCount ++;
                    continue;
                }else {
                    continue;
                }
            }
            entity.setSdCount(sdCount);
            entity.setShAndpjCount(shAndpjCount);
            entity.setShCount(shCount);
            entity.setPjCount(pjCount);
            //----------------------------------统计完成比率-------------------------------------------------------
            List<Criterion> list1 = new ArrayList<>();
            list1.add(Restrictions.eq("ownerid", user.getId()));
            list1.add(Restrictions.ge("assigndate", startDate));
            list1.add(Restrictions.le("assigndate", endDate));
            List<TaskEntity> tasks1 = this.sysUserService.getDataList(TaskEntity.class,list1,null);
            float sdPercent =0f;        //下单完成率
            float shAndPjPercent = 0f;  //收货且评价完成率
            float shPercent = 0f;       //收货完成率
            float pjPercent = 0f;       //评价完成率
            if(tasks1.size()>0){
                int sd = 0;            //下单总数
                int shAndpj = 0;       //收货且评价数量
                int sh = 0;            //收货数量
                int pj = 0;            //评价数量

                int sdComplete = 0;            //下单任务总数
                int shAndpjComplete = 0;       //收货且评价任务数量
                int shComplete = 0;            //收货任务数量
                int pjComplete = 0;            //评价任务数量

                for (TaskEntity task :tasks1){
                    if("01".equals(task.getTasktype())){
                        sd++;
                        if(task.getTaskstate() == 2){
                            sdComplete ++;
                        }
                        continue;
                    }else if("02".equals(task.getTasktype())){
                        shAndpj ++;
                        if(task.getTaskstate() == 2){
                            shAndpjComplete ++;
                        }
                        continue;
                    }else if("03".equals(task.getTasktype())){
                        sh ++;
                        if(task.getTaskstate() == 2){
                            shComplete ++;
                        }
                        continue;
                    }else if("04".equals(task.getTasktype())){
                        pj ++;
                        if(task.getTaskstate() == 2){
                            pjComplete ++;
                        }
                        continue;
                    }else {
                        continue;
                    }
                }


                sdPercent = ((sdComplete*1.0f)/(sd*1.0f))*100/100;
                shAndPjPercent = (shAndpjComplete*1.0f)/(shAndpj*1.0f);
                shPercent = (shComplete*1.0f)/(sh*1.0f);
                pjPercent = (pjComplete*1.0f)/(pj*1.0f);
            }
            entity.setSdPercent(Math.round(sdPercent*100));
            entity.setShAndPjPercent(Math.round(shAndPjPercent*100));
            entity.setShPercent(Math.round(shPercent*100));
            entity.setPjPercent(Math.round(pjPercent*100));

            dataList.add(entity);
        }
        return dataList;
    }
}
