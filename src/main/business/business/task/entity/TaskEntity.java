package business.task.entity;

import com.code.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity   extends BaseEntity{
    //任务类型   01:刷单任务，02：收货且评价，03：收货，04：评价
    @Column(name="taskType" ,length = 10  )
    private String  tasktype;
    //SKU
    @Column(name="sku" ,length = 100  )
    private String  sku;
    //刷单方式
    @Column(name="sdfs" ,length = 10  )
    private String  sdfs;
    //关键词
    @Column(name="keyword" ,length = 100  )
    private String  keyword;
    //备注
    @Column(name="mark" ,length = 100  )
    private String  mark;
    //订单编号
    @Column(name="orderNun" ,length = 100  )
    private String  ordernun;
    //评价文字
    @Column(name="pjwz" ,length = 200  )
    private String  pjwz;
    //是否晒图
    @Column(name="sfst" ,length = 10  )
    private String  sfst;
    //图片名称
    @Column(name="picture" ,length = 200  )
    private String  picture;
    //任务状态
    @Column(name="taskState"  )
    private Integer  taskstate = 0 ;
    //完成时间
    @Column(name="completeDate"  )
    private java.util.Date  completedate;
    //所属人
    @Column(name="taskOwner" ,length = 100  )
    private String  taskowner;
    //所属人ID
    @Column(name="ownerId" ,length = 100  )
    private String  ownerid;
    //店铺名
    @Column(name="shopName" ,length = 200  )
    private String  shopname;
    //分配时间
    @Column(name="assignDate"  )
    private java.util.Date  assigndate;

    public TaskEntity(){

    }

    public TaskEntity(String id){
        setId(id);
    }

    public String getTasktype(){
        return tasktype;
    }
    public void setTasktype(String tasktype){
        this.tasktype=tasktype;
    }
    public String getSku(){
        return sku;
    }
    public void setSku(String sku){
        this.sku=sku;
    }
    public String getSdfs(){
        return sdfs;
    }
    public void setSdfs(String sdfs){
        this.sdfs=sdfs;
    }
    public String getKeyword(){
        return keyword;
    }
    public void setKeyword(String keyword){
        this.keyword=keyword;
    }
    public String getMark(){
        return mark;
    }
    public void setMark(String mark){
        this.mark=mark;
    }
    public String getOrdernun(){
        return ordernun;
    }
    public void setOrdernun(String ordernun){
        this.ordernun=ordernun;
    }
    public String getPjwz(){
        return pjwz;
    }
    public void setPjwz(String pjwz){
        this.pjwz=pjwz;
    }
    public String getSfst(){
        return sfst;
    }
    public void setSfst(String sfst){
        this.sfst=sfst;
    }
    public String getPicture(){
        return picture;
    }
    public void setPicture(String picture){
        this.picture=picture;
    }
    public Integer getTaskstate(){
        return taskstate;
    }
    public void setTaskstate(Integer taskstate){
        this.taskstate=taskstate;
    }
    public java.util.Date getCompletedate(){
        return completedate;
    }
    public void setCompletedate(java.util.Date completedate){
        this.completedate=completedate;
    }
    public String getTaskowner(){
        return taskowner;
    }
    public void setTaskowner(String taskowner){
        this.taskowner=taskowner;
    }
    public String getOwnerid(){
        return ownerid;
    }
    public void setOwnerid(String ownerid){
        this.ownerid=ownerid;
    }
    public String getShopname(){
        return shopname;
    }
    public void setShopname(String shopname){
        this.shopname=shopname;
    }
    public java.util.Date getAssigndate(){
        return assigndate;
    }
    public void setAssigndate(java.util.Date assigndate){
        this.assigndate=assigndate;
    }
}
