package business.setting.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import com.code.entity.BaseEntity;

@Entity
@Table(name = "setting")
public class SettingEntity   extends BaseEntity{
    //同店疲劳期
    @Column(name="tdplq"  )
    private Integer  tdplq = 0 ;
    //同SKU疲劳期
    @Column(name="tskuplq"  )
    private Integer  tskuplq = 0 ;
    //月最多购买件数
    @Column(name="gmjs"  )
    private Integer  gmjs = 0 ;
    //月最多购买金额
    @Column(name="gmje"  )
    private Float  gmje;

    public SettingEntity(){

    }

    public SettingEntity(String id){
        setId(id);
    }

    public Integer getTdplq(){
        return tdplq;
    }
    public void setTdplq(Integer tdplq){
        this.tdplq=tdplq;
    }
    public Integer getTskuplq(){
        return tskuplq;
    }
    public void setTskuplq(Integer tskuplq){
        this.tskuplq=tskuplq;
    }
    public Integer getGmjs(){
        return gmjs;
    }
    public void setGmjs(Integer gmjs){
        this.gmjs=gmjs;
    }
    public Float getGmje(){
        return gmje;
    }
    public void setGmje(Float gmje){
        this.gmje=gmje;
    }
}
