package business.shop.entity;

import com.code.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class ShopEntity   extends BaseEntity{
    //商家名
    @Column(name="name" ,length = 100  )
    private String  name;
    //商家账号
    @Column(name="shopAccount" ,length = 100  )
    private String  shopaccount;
    //货款
    @Column(name="payment"  )
    private Float  payment;
    //佣金
    @Column(name="commission"  )
    private Float  commission;
    //刷单价格APP
    @Column(name="sd_app"  )
    private Float  sdApp;
    //刷单价格PC
    @Column(name="sd_pc"  )
    private Float  sdPc;
    //收货且评价价格
    @Column(name="sh_pj"  )
    private Float  shPj;
    //收货价格
    @Column(name="sh"  )
    private Float  sh;
    //合作时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="cooperateDate"  )
    private java.util.Date  cooperatedate;
    //评价价格
    @Column(name="pj"  )
    private Float  pj;

    public ShopEntity(){

    }

    public ShopEntity(String id){
        setId(id);
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getShopaccount(){
        return shopaccount;
    }
    public void setShopaccount(String shopaccount){
        this.shopaccount=shopaccount;
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
    public Float getSdApp(){
        return sdApp;
    }
    public void setSdApp(Float sdApp){
        this.sdApp=sdApp;
    }
    public Float getSdPc(){
        return sdPc;
    }
    public void setSdPc(Float sdPc){
        this.sdPc=sdPc;
    }
    public Float getShPj(){
        return shPj;
    }
    public void setShPj(Float shPj){
        this.shPj=shPj;
    }
    public Float getSh(){
        return sh;
    }
    public void setSh(Float sh){
        this.sh=sh;
    }
    public java.util.Date getCooperatedate(){
        return cooperatedate;
    }
    public void setCooperatedate(java.util.Date cooperatedate){
        this.cooperatedate=cooperatedate;
    }
    public Float getPj(){
        return pj;
    }
    public void setPj(Float pj){
        this.pj=pj;
    }
}
