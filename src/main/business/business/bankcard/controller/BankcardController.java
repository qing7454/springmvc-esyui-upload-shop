package business.bankcard.controller;

import com.sys.constant.Globals;
import business.bankcard.entity.BankcardEntity;
import business.bankcard.service.BankcardService;
import com.sys.entity.ComboBox;
import com.sys.entity.SessionUser;
import com.sys.service.SysDepService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import com.sys.service.ISystemService;
import com.sys.util.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/business/bankcard")
public class BankcardController {
    @Resource
    private BankcardService bankcardService;
    @Resource
    private ISystemService systemService;

    /**
     * 转入页面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/bankcard/bankcard_list";
    }

    /**
     * 获取数据列表
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d, HttpServletRequest request) {
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list = BeanUtil.generateCriterions(BankcardEntity.class, request, false);
        bankcardService.fillDataGrid(BankcardEntity.class, list, d);
        return d;
    }

    /**
     * 保存或更新
     *
     * @param bean
     * @return
     */
    @RequestMapping(params = "save")
    @ResponseBody
    public SuccessMsg save(BankcardEntity bean) {
        SuccessMsg json = new SuccessMsg();
        String logType = Globals.LOG_INSERT;
        Map<String, Object> dataMap = new HashMap<>();
        if (StringUtils.isBlank(bean.getId())) {
            bean.setId(null);
            Serializable id = bankcardService.saveReturnId(bean);
            json.setSuccess(id != null);
            try {
                BeanUtils.setProperty(bean, "id", id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            BankcardEntity bankcardEntity = bankcardService.getEntity(BankcardEntity.class, bean.getId());
            if (bankcardEntity == null) bankcardEntity = new BankcardEntity();
            BeanUtil.copyNotNull2Bean(bean, bankcardEntity);
            json.setSuccess(bankcardService.update(bankcardEntity));
            logType = Globals.LOG_UPDATE;
        }
        dataMap.put("bean", bean);
        json.setDataMap(dataMap);
        if (json.isSuccess()) json.setMsg("保存成功!");
        else json.setMsg("保存失败！");
        systemService.addLog(BankcardEntity.class.getSimpleName() + json.getMsg(), logType);
        return json;
    }

    /**
     * 获取实体bean 返回json
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "get")
    @ResponseBody
    public BankcardEntity get(String id) {
        Assert.notNull(id);
        return bankcardService.getEntity(BankcardEntity.class, id);
    }

    /**
     * 获取详情
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(params = "detail")
    public String detail(String id, HttpServletRequest request) {
        Assert.notNull(id);
        request.setAttribute("bean", bankcardService.getEntity(BankcardEntity.class, id));
        return "business/bankcard/bankcard_detail";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public SuccessMsg del(String id) {
        SuccessMsg j = new SuccessMsg();
        BankcardEntity d = new BankcardEntity();
        d.setId(id);
        j.setSuccess(bankcardService.delete(d));
        if (j.isSuccess())
            j.setMsg("删除成功!");
        else
            j.setMsg("删除失败！");
        systemService.addLog(BankcardEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
        return j;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "muldel")
    @ResponseBody
    public SuccessMsg mulDel(String[] ids) {
        SuccessMsg j = new SuccessMsg();
        if (ids != null) {
            j.setSuccess(true);
            j.setMsg("成功删除" + ids.length + "条记录");
            for (int i = 0; i < ids.length; i++) {
                if (!bankcardService.delete(new BankcardEntity(ids[i]))) {
                    j.setSuccess(false);
                    j.setMsg("删除失败!");
                }
                systemService.addLog(BankcardEntity.class.getSimpleName() + j.getMsg(), Globals.LOG_DEL);
            }
        }
        return j;
    }

    /**
     * 获取银行卡信息
     * @return
     */
    @RequestMapping(params = "getBankCard")
    @ResponseBody
    public List<ComboBox> getBankCard(){
        List<ComboBox> comboBoxes = new ArrayList<>();
        List<BankcardEntity> cards = this.bankcardService.getDataList(BankcardEntity.class,null,null);
        for(BankcardEntity card:cards){
            ComboBox box = new ComboBox();
            box.setcKey(card.getId());
            box.setcValue(card.getCardnum());
            comboBoxes.add(box);
        }
        return comboBoxes;
    }
}
