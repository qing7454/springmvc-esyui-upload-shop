package com.print.entity;

import com.code.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "base_print_template")
public class BasePrintTemplateEntity   extends BaseEntity{
    //模板名称
    @Column(name="m_name" ,length = 100  ,nullable = true)
    private String  mName;
    //表名
    @Column(name="m_tablename" ,length = 100  ,nullable = true)
    private String  mTablename;
    //字段key
    @Column(name="m_field" ,length = 100  ,nullable = true)
    private String  mField;
    //字段值
    @Column(name="m_value" ,length = 100  ,nullable = true)
    private String  mValue;

    public BasePrintTemplateEntity(){

    }

    public BasePrintTemplateEntity(String id){
        setId(id);
    }

    public String getMName(){
        return mName;
    }
    public void setMName(String mName){
        this.mName=mName;
    }
    public String getMTablename(){
        return mTablename;
    }
    public void setMTablename(String mTablename){
        this.mTablename=mTablename;
    }
    public String getMField(){
        return mField;
    }
    public void setMField(String mField){
        this.mField=mField;
    }
    public String getMValue(){
        return mValue;
    }
    public void setMValue(String mValue){
        this.mValue=mValue;
    }
}
