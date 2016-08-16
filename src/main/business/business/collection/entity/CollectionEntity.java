package business.collection.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import com.code.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "collection")
public class CollectionEntity   extends BaseEntity{
    //收款类型
    @Column(name="collection_type" ,length = 10  )
    private String  collectionType;
    //商家
    @Column(name="shopId" ,length = 50  )
    private String  shopid;
    //商家编码
    @Column(name="shopNum" ,length = 100  )
    private String  shopnum;
    //收款日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="collection_date"  )
    private java.util.Date  collectionDate;
    //收款账户
    @Column(name="collection_account" ,length = 100  )
    private String  collectionAccount;
    //打款账户
    @Column(name="pay_account" ,length = 100  )
    private String  payAccount;
    //收款金额
    @Column(name="collection_money"  )
    private Float  collectionMoney;

    public CollectionEntity(){

    }

    public CollectionEntity(String id){
        setId(id);
    }

    public String getCollectionType(){
        return collectionType;
    }
    public void setCollectionType(String collectionType){
        this.collectionType=collectionType;
    }
    public String getShopid(){
        return shopid;
    }
    public void setShopid(String shopid){
        this.shopid=shopid;
    }
    public String getShopnum(){
        return shopnum;
    }
    public void setShopnum(String shopnum){
        this.shopnum=shopnum;
    }
    public java.util.Date getCollectionDate(){
        return collectionDate;
    }
    public void setCollectionDate(java.util.Date collectionDate){
        this.collectionDate=collectionDate;
    }
    public String getCollectionAccount(){
        return collectionAccount;
    }
    public void setCollectionAccount(String collectionAccount){
        this.collectionAccount=collectionAccount;
    }
    public String getPayAccount(){
        return payAccount;
    }
    public void setPayAccount(String payAccount){
        this.payAccount=payAccount;
    }
    public Float getCollectionMoney(){
        return collectionMoney;
    }
    public void setCollectionMoney(Float collectionMoney){
        this.collectionMoney=collectionMoney;
    }
}
