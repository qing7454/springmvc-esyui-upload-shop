package business.account.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import com.code.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "account")
public class AccountEntity   extends BaseEntity{
    //账号
    @Column(name="account" ,length = 100  )
    private String  account;
    //登录密码
    @Column(name="password_login" ,length = 100  )
    private String  passwordLogin;
    //支付密码
    @Column(name="password_pay" ,length = 100  )
    private String  passwordPay;
    //邮箱
    @Column(name="email" ,length = 100  )
    private String  email;
    //邮箱密码
    @Column(name="password_email" ,length = 100  )
    private String  passwordEmail;
    //地区
    @Column(name="area" ,length = 100  )
    private String  area;
    //运营商
    @Column(name="operator" ,length = 100  )
    private String  operator;
    //账号等级
    @Column(name="level" ,length = 100  )
    private String  level;
    //身份账号
    @Column(name="idcard" ,length = 100  )
    private String  idcard;
    //姓名
    @Column(name="name" ,length = 100  )
    private String  name;
    //异常原因
    @Column(name="exception" ,length = 500  )
    private String  exception;
    //删除原因
    @Column(name="deleteReason" ,length = 500  )
    private String  deletereason;
    //注册日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="registerDate"  )
    private java.util.Date  registerdate;
    //账号状态 01：正常，02：异常，03：删除
    @Column(name="accountState" ,length = 100  )
    private String  accountstate;
    //手机号
    @Column(name="phone" ,length = 100  )
    private String  phone;
    //收货地址
    @Column(name="address" ,length = 200  )
    private String  address;

    public AccountEntity(){

    }

    public AccountEntity(String id){
        setId(id);
    }

    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account=account;
    }
    public String getPasswordLogin(){
        return passwordLogin;
    }
    public void setPasswordLogin(String passwordLogin){
        this.passwordLogin=passwordLogin;
    }
    public String getPasswordPay(){
        return passwordPay;
    }
    public void setPasswordPay(String passwordPay){
        this.passwordPay=passwordPay;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPasswordEmail(){
        return passwordEmail;
    }
    public void setPasswordEmail(String passwordEmail){
        this.passwordEmail=passwordEmail;
    }
    public String getArea(){
        return area;
    }
    public void setArea(String area){
        this.area=area;
    }
    public String getOperator(){
        return operator;
    }
    public void setOperator(String operator){
        this.operator=operator;
    }
    public String getLevel(){
        return level;
    }
    public void setLevel(String level){
        this.level=level;
    }
    public String getIdcard(){
        return idcard;
    }
    public void setIdcard(String idcard){
        this.idcard=idcard;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getException(){
        return exception;
    }
    public void setException(String exception){
        this.exception=exception;
    }
    public String getDeletereason(){
        return deletereason;
    }
    public void setDeletereason(String deletereason){
        this.deletereason=deletereason;
    }
    public java.util.Date getRegisterdate(){
        return registerdate;
    }
    public void setRegisterdate(java.util.Date registerdate){
        this.registerdate=registerdate;
    }
    public String getAccountstate(){
        return accountstate;
    }
    public void setAccountstate(String accountstate){
        this.accountstate=accountstate;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
}
