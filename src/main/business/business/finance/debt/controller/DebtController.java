package business.finance.debt.controller;

import business.finance.commission.service.CommissionService;
import business.finance.debt.entity.DebtEntity;
import business.finance.debt.service.DebtService;
import business.order.service.TOrderService;
import business.shop.entity.ShopEntity;
import com.sys.util.BeanUtil;
import com.sys.util.ContextHolderUtil;
import com.sys.util.DataGrid;
import com.sys.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/business/finance/debt")
public class DebtController {
    @Resource
    private DebtService debtService;
    @Resource
    private CommissionService commissionService;

    @Resource
    private TOrderService tOrderService;

    /**
     * 转入页面
     *
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request) {
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/finance/debt/debt_list";
    }

    /**
     * 获取数据列表
     *
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d, HttpServletRequest request) {
        if (StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list = BeanUtil.generateCriterions(ShopEntity.class, request, false);
        debtService.fillDataGrid(ShopEntity.class, list, d);
        List<ShopEntity> shops = d.getDataList();
        List<DebtEntity> debts = new ArrayList<>();
        Float debtAmount = 0f;
        Float paymentAmount = 0f;
        Float commissionAmount = 0f;
        for (ShopEntity shop : shops) {
            DebtEntity entity = new DebtEntity();
            entity.setShopName(shop.getName());
            entity.setCommission(shop.getCommission());
            entity.setPayment(shop.getPayment());
            entity.setAmmount(shop.getCommission() + shop.getPayment());
            debtAmount = debtAmount + entity.getAmmount();
            paymentAmount = paymentAmount + entity.getPayment();
            commissionAmount = commissionAmount + entity.getCommission();
            debts.add(entity);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("debtAmount", debtAmount);
        dataMap.put("paymentAmount",paymentAmount);
        dataMap.put("commissionAmount",commissionAmount);
        d.setDataMap(dataMap);
        d.setDataList(debts);
        return d;
    }


}
