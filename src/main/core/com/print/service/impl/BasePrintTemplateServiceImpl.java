package com.print.service.impl;

import com.print.entity.BasePrintTemplateEntity;
import com.print.service.BasePrintTemplateService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("basePrintTemplateService")
@Transactional
public class BasePrintTemplateServiceImpl extends CommonService implements BasePrintTemplateService {
    @Override
    public boolean bulkSave(String tableName,List<BasePrintTemplateEntity> list){
       updateByHql("delete from BasePrintTemplateEntity where mTablename=? and mName=?",new Object[]{tableName,list.get(0).getMName()});
       for(BasePrintTemplateEntity entity:list){
           entity.setId(null);
           save(entity);
       }
        return true;
    }

    @Override
    public List<String> getTemplateNamesByTableName(String tableName) {
        return getDataListByHql("select distinct(mName) from BasePrintTemplateEntity where mTablename=?", new Object[]{tableName});
    }

    @Override
    public List<BasePrintTemplateEntity> getTemplateByTableNameAndTemplateName(String tableName, String templateName) {
        return getDataListByHql("from BasePrintTemplateEntity where mTablename=? and mName=?", new Object[]{tableName,templateName});
    }

    @Override
    public boolean delTemplate(String tableName, String templateName) {
        return updateByHql("delete from BasePrintTemplateEntity where mTablename=? and mName=?",new Object[]{tableName,templateName})>0;
    }
}
