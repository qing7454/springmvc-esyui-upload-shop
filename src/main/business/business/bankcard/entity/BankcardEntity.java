package business.bankcard.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import com.code.entity.BaseEntity;

@Entity
@Table(name = "bankcard")
public class BankcardEntity   extends BaseEntity{
    //银行名称
    @Column(name="bankname" ,length = 100  )
    private String  bankname;
    //卡号
    @Column(name="cardnum" ,length = 100  )
    private String  cardnum;
    //备注
    @Column(name="mark" ,length = 100  )
    private String  mark;
    //密码
    @Column(name="password" ,length = 100  )
    private String  password;

    public BankcardEntity(){

    }

    public BankcardEntity(String id){
        setId(id);
    }

    public String getBankname(){
        return bankname;
    }
    public void setBankname(String bankname){
        this.bankname=bankname;
    }
    public String getCardnum(){
        return cardnum;
    }
    public void setCardnum(String cardnum){
        this.cardnum=cardnum;
    }
    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark=mark;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
