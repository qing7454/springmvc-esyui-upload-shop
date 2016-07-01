/*
 *  SupervisionEntity
 * ?Created?on?2016/6/17 14:51
 * ?
 * ?版本???????修改时间??????????作者??????  修改内容?
 * ?V0.1????   2016/6/17??????    程洪强???  ?初始版本?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK项目组?版权所有?
 * ?SK project team?All?Rights?Reserved.?
 */
package business.task.entity;

/**
 * SupervisionEntity
 * Created by 程洪强 on 2016/6/17.
 */
public class SupervisionEntity {
    //用户ID
    private String userId;

    //用户昵称
    private String realName;

    //任务总数
    private Integer taskCount;

    //完成数
    private Integer complete;

    //未完成数
    private Integer unComplete;

    //完成百分比
    private Float percent;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getUnComplete() {
        return unComplete;
    }

    public void setUnComplete(Integer unComplete) {
        this.unComplete = unComplete;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }
}
