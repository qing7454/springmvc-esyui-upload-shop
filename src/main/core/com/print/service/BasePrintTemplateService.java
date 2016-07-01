package com.print.service;

import com.print.entity.BasePrintTemplateEntity;
import com.sys.service.ICommonService;

import java.util.List;

public interface BasePrintTemplateService  extends ICommonService {
    /**
     * 批量保存
     * @param tableName
     * @param list
     * @return
     */
    public boolean bulkSave(String tableName,List<BasePrintTemplateEntity> list);

    /**
     * 通过表名获取模板名称
     * @param tableName
     * @return
     */
    public List<String> getTemplateNamesByTableName(String tableName);
    /**
     * 通过表名和模板名获取模板名称
     * @param tableName
     * @return
     */
    public List<BasePrintTemplateEntity> getTemplateByTableNameAndTemplateName(String tableName,String templateName);

    /**
     * 删除模板
     * @param tableName
     * @param templateName
     * @return
     */
    public boolean delTemplate(String tableName,String templateName);
}
