/*
 *  AnalyzeEntity
 * ?Created?on?2016/6/24 14:46
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
package business.analyze.entity;

/**
 * AnalyzeEntity
 * Created by 程洪强 on 2016/6/24.
 */
public class AnalyzeEntity {

    private String userId;

    private String userName;

    //注册数量
    private int registerCount;

    //下单数量
    private int sdCount;

    //收货且评价数量
    private int shAndpjCount;

    //收货数量
    private int shCount;

    //评价数量
    private int pjCount;

    //解异常数量
    private int removeException;

    //刷单完成率
    private float sdPercent;

    //收货且评价完成率
    private float shAndPjPercent;

    //收货完成率
    private float shPercent;

    //评价完成率
    private float pjPercent;

    //出错免单总数
    private int errCount;

    //出错免单总佣金
    private int errCommission;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }

    public int getSdCount() {
        return sdCount;
    }

    public void setSdCount(int sdCount) {
        this.sdCount = sdCount;
    }

    public int getShAndpjCount() {
        return shAndpjCount;
    }

    public void setShAndpjCount(int shAndpjCount) {
        this.shAndpjCount = shAndpjCount;
    }

    public int getShCount() {
        return shCount;
    }

    public void setShCount(int shCount) {
        this.shCount = shCount;
    }

    public int getPjCount() {
        return pjCount;
    }

    public void setPjCount(int pjCount) {
        this.pjCount = pjCount;
    }

    public int getRemoveExccception() {
        return removeException;
    }

    public void setRemoveExccception(int removeExccception) {
        this.removeException = removeExccception;
    }

    public int getRemoveException() {
        return removeException;
    }

    public void setRemoveException(int removeException) {
        this.removeException = removeException;
    }

    public float getSdPercent() {
        return sdPercent;
    }

    public void setSdPercent(float sdPercent) {
        this.sdPercent = sdPercent;
    }

    public float getShAndPjPercent() {
        return shAndPjPercent;
    }

    public void setShAndPjPercent(float shAndPjPercent) {
        this.shAndPjPercent = shAndPjPercent;
    }

    public float getShPercent() {
        return shPercent;
    }

    public void setShPercent(float shPercent) {
        this.shPercent = shPercent;
    }

    public float getPjPercent() {
        return pjPercent;
    }

    public void setPjPercent(float pjPercent) {
        this.pjPercent = pjPercent;
    }

    public int getErrCount() {
        return errCount;
    }

    public void setErrCount(int errCount) {
        this.errCount = errCount;
    }

    public int getErrCommission() {
        return errCommission;
    }

    public void setErrCommission(int errCommission) {
        this.errCommission = errCommission;
    }
}
