package business.goods.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import com.code.entity.BaseEntity;

@Entity
@Table(name = "goods")
public class GoodsEntity   extends BaseEntity{
    //商品ID
    @Column(name="goodId" ,length = 100  )
    private String  goodid;
    //商品名称
    @Column(name="goodName" ,length = 100  )
    private String  goodname;
    //价格
    @Column(name="price"  )
    private Float  price;
    //备注
    @Column(name="mark" ,length = 100  )
    private String  mark;
    //商家ID
    @Column(name="shopId" ,length = 100  )
    private String  shopid;

    public GoodsEntity(){

    }

    public GoodsEntity(String id){
        setId(id);
    }

    public String getGoodid(){
        return goodid;
    }
    public void setGoodid(String goodid){
        this.goodid=goodid;
    }
    public String getGoodname(){
        return goodname;
    }
    public void setGoodname(String goodname){
        this.goodname=goodname;
    }
    public Float getPrice(){
        return price;
    }
    public void setPrice(Float price){
        this.price=price;
    }
    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark=mark;
    }
    public String getShopid(){
        return shopid;
    }
    public void setShopid(String shopid){
        this.shopid=shopid;
    }
}
