/*
 *  AnalyzeEntity
 * ?Created?on?2016/6/24 14:46
 * ?
 * ?�汾???????�޸�ʱ��??????????����??????  �޸�����?
 * ?V0.1????   2016/6/24??????    �̺�ǿ???  ?��ʼ�汾?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK��Ŀ��?��Ȩ����?
 * ?SK project team?All?Rights?Reserved.?
 */
package business.analyze.entity;

/**
 * AnalyzeEntity
 * Created by �̺�ǿ on 2016/6/24.
 */
public class AnalyzeEntity {

    private String userId;

    private String userName;

    //ע������
    private int registerCount;

    //�µ�����
    private int sdCount;

    //�ջ�����������
    private int shAndpjCount;

    //�ջ�����
    private int shCount;

    //��������
    private int pjCount;

    //���쳣����
    private int removeException;

    //ˢ�������
    private float sdPercent;

    //�ջ������������
    private float shAndPjPercent;

    //�ջ������
    private float shPercent;

    //���������
    private float pjPercent;

    //�����ⵥ����
    private int errCount;

    //�����ⵥ��Ӷ��
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
