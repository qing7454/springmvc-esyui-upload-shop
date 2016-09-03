package business.order.entity;

import com.code.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class TOrderEntity extends BaseEntity{
    //订单编号
    @Column(name="ddNum" ,length = 100  )
    private String  ddnum;
    //任务编号
    @Column(name="taskId" ,length = 100  )
    private String  taskid;
    //下单时间
    @Column(name="xdsj"  )
    private java.util.Date  xdsj;
    //收货时间
    @Column(name="shDate"  )
    private java.util.Date  shdate;
    //评价时间
    @Column(name="pjDate"  )
    private java.util.Date  pjdate;
    //下单人员
    @Column(name="xdPersonId" ,length = 100  )
    private String  xdpersonid;
    //收货人员
    @Column(name="shPersionId" ,length = 100  )
    private String  shpersionid;
    //评价人员
    @Column(name="pjPsersionId" ,length = 100  )
    private String  pjpsersionid;
    //订单状态  0:已下单，1：已收货，2：已评价，3：以免单
    @Column(name="djState"  )
    private Integer  djstate = 0 ;
    //银行卡
    @Column(name="bankcard" ,length = 100  )
    private String  bankcard;
    //货款
    @Column(name="payment"  )
    private Float  payment;
    //下单佣金
    @Column(name="commission_xd"  )
    private Float  commissionXd = 0f;
    //收货评价佣金
    @Column(name="commission_sh_pj"  )
    private Float  commissionShPj = 0f;
    //收货佣金
    @Column(name="commission_sh"  )
    private Float  commissionSh = 0f;
    //评价佣金
    @Column(name="commission_pj"  )
    private Float  commissionPj = 0f;
    //账号ID
    @Column(name="accountId" ,length = 100  )
    private String  accountid;
    //SKUID
    @Column(name="goodId" ,length = 100  )
    private String  goodid;
    //商品编号
    @Column(name="goodNum" ,length = 100  )
    private String  goodnum;
    //账号
    @Column(name="account" ,length = 100  )
    private String  account;
    //店铺名称
    @Column(name="shopName" ,length = 100  )
    private String  shopname;
    //店铺ID
    @Column(name="shopId" ,length = 100  )
    private String  shopid;
    //收菜类型 0,任务收菜，1：自由收菜
    @Column(name="sclx" ,length = 2  )
    private String  sclx;
    public TOrderEntity(){

    }

    public TOrderEntity(String id){
        setId(id);
    }

    public String getDdnum(){
        return ddnum;
    }
    public void setDdnum(String ddnum){
        this.ddnum=ddnum;
    }
    public String getTaskid(){
        return taskid;
    }
    public void setTaskid(String taskid){
        this.taskid=taskid;
    }
    public java.util.Date getXdsj(){
        return xdsj;
    }
    public void setXdsj(java.util.Date xdsj){
        this.xdsj=xdsj;
    }
    public java.util.Date getShdate(){
        return shdate;
    }
    public void setShdate(java.util.Date shdate){
        this.shdate=shdate;
    }
    public java.util.Date getPjdate(){
        return pjdate;
    }
    public void setPjdate(java.util.Date pjdate){
        this.pjdate=pjdate;
    }
    public String getXdpersonid(){
        return xdpersonid;
    }
    public void setXdpersonid(String xdpersonid){
        this.xdpersonid=xdpersonid;
    }
    public String getShpersionid(){
        return shpersionid;
    }
    public void setShpersionid(String shpersionid){
        this.shpersionid=shpersionid;
    }
    public String getPjpsersionid(){
        return pjpsersionid;
    }
    public void setPjpsersionid(String pjpsersionid){
        this.pjpsersionid=pjpsersionid;
    }
    public Integer getDjstate(){
        return djstate;
    }
    public void setDjstate(Integer djstate){
        this.djstate=djstate;
    }
    public String getBankcard(){
        return bankcard;
    }
    public void setBankcard(String bankcard){
        this.bankcard=bankcard;
    }
    public Float getPayment(){
        return payment;
    }
    public void setPayment(Float payment){
        this.payment=payment;
    }
    public Float getCommissionXd(){
        return commissionXd;
    }
    public void setCommissionXd(Float commissionXd){
        this.commissionXd=commissionXd;
    }
    public Float getCommissionShPj(){
        return commissionShPj;
    }
    public void setCommissionShPj(Float commissionShPj){
        this.commissionShPj=commissionShPj;
    }
    public Float getCommissionSh(){
        return commissionSh;
    }
    public void setCommissionSh(Float commissionSh){
        this.commissionSh=commissionSh;
    }
    public Float getCommissionPj(){
        return commissionPj;
    }
    public void setCommissionPj(Float commissionPj){
        this.commissionPj=commissionPj;
    }
    public String getAccountid(){
        return accountid;
    }
    public void setAccountid(String accountid){
        this.accountid=accountid;
    }
    public String getGoodid(){
        return goodid;
    }
    public void setGoodid(String goodid){
        this.goodid=goodid;
    }
    public String getGoodnum(){
        return goodnum;
    }
    public void setGoodnum(String goodnum){
        this.goodnum=goodnum;
    }
    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account=account;
    }
    public String getShopname(){
        return shopname;
    }
    public void setShopname(String shopname){
        this.shopname=shopname;
    }
    public String getShopid(){
        return shopid;
    }
    public void setShopid(String shopid){
        this.shopid=shopid;
    }

    public String getSclx() {
        return sclx;
    }

    public void setSclx(String sclx) {
        this.sclx = sclx;
    }
}
