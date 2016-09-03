package business.finance.debt.entity;

import javax.persistence.Column;


public class DebtEntity {
    //商家名称
    @Column(name="shop_name" ,length = 100  )
    private String  shopName;
    //欠货款总额
    @Column(name="payment"  )
    private Float  payment;
    //欠佣金总额
    @Column(name="commission"  )
    private Float  commission;
    //总计
    @Column(name="ammount"  )
    private Float  ammount;
    //开始时间
    @Column(name="start_date"  )
    private java.util.Date  startDate;
    //结束时间
    @Column(name="end_date"  )
    private java.util.Date  endDate;

    public DebtEntity(){

    }


    public String getShopName(){
        return shopName;
    }
    public void setShopName(String shopName){
        this.shopName=shopName;
    }
    public Float getPayment(){
        return payment;
    }
    public void setPayment(Float payment){
        this.payment=payment;
    }
    public Float getCommission(){
        return commission;
    }
    public void setCommission(Float commission){
        this.commission=commission;
    }
    public Float getAmmount(){
        return ammount;
    }
    public void setAmmount(Float ammount){
        this.ammount=ammount;
    }
    public java.util.Date getStartDate(){
        return startDate;
    }
    public void setStartDate(java.util.Date startDate){
        this.startDate=startDate;
    }
    public java.util.Date getEndDate(){
        return endDate;
    }
    public void setEndDate(java.util.Date endDate){
        this.endDate=endDate;
    }
}
