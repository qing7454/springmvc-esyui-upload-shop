package business.finance.commission.entity;

/**
 * CommissionEntity
 * Created by �̺�ǿ on 2016/8/29.
 */
public class CommissionEntity {

    /** ��������  **/
    private String shopName;
    /** �µ�Ӷ�� **/
    private Float commissionXd;
    /** �ջ�����Ӷ�� **/
    private Float commissionShPj;
    /** �ջ�Ӷ�� **/
    private Float commissionSh;
    /** ����Ӷ�� **/
    private Float commissionPj;

    /** Ӷ���ܼ� **/
    private Float amount;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Float getCommissionXd() {
        return commissionXd;
    }

    public void setCommissionXd(Float commissionXd) {
        this.commissionXd = commissionXd;
    }

    public Float getCommissionShPj() {
        return commissionShPj;
    }

    public void setCommissionShPj(Float commissionShPj) {
        this.commissionShPj = commissionShPj;
    }

    public Float getCommissionSh() {
        return commissionSh;
    }

    public void setCommissionSh(Float commissionSh) {
        this.commissionSh = commissionSh;
    }

    public Float getCommissionPj() {
        return commissionPj;
    }

    public void setCommissionPj(Float commissionPj) {
        this.commissionPj = commissionPj;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
