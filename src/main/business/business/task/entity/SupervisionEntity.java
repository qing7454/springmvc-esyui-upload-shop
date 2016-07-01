/*
 *  SupervisionEntity
 * ?Created?on?2016/6/17 14:51
 * ?
 * ?�汾???????�޸�ʱ��??????????����??????  �޸�����?
 * ?V0.1????   2016/6/17??????    �̺�ǿ???  ?��ʼ�汾?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK��Ŀ��?��Ȩ����?
 * ?SK project team?All?Rights?Reserved.?
 */
package business.task.entity;

/**
 * SupervisionEntity
 * Created by �̺�ǿ on 2016/6/17.
 */
public class SupervisionEntity {
    //�û�ID
    private String userId;

    //�û��ǳ�
    private String realName;

    //��������
    private Integer taskCount;

    //�����
    private Integer complete;

    //δ�����
    private Integer unComplete;

    //��ɰٷֱ�
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
