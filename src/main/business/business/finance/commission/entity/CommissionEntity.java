package business.finance.commission.entity;

/**
 * CommissionEntity
 * Created by 程洪强 on 2016/8/29.
 */
public class CommissionEntity {

    /** 店铺名称  **/
    private String shopName;
    /** 下单佣金 **/
    private Float commissionXd;
    /** 收货评价佣金 **/
    private Float commissionShPj;
    /** 收货佣金 **/
    private Float commissionSh;
    /** 评价佣金 **/
    private Float commissionPj;

    /** 佣金总计 **/
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
