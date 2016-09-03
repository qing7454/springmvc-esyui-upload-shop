/*
 *  FreeOrderController
 * ?Created?on?2016/8/22 15:14
 * ?
 * ?版本???????修改时间??????????作者??????  修改内容?
 * ?V0.1????   2016/8/22??????    程洪强???  ?初始版本?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK项目组?版权所有?
 * ?SK project team?All?Rights?Reserved.?
 */
package business.freeorder;

import business.goods.service.GoodsService;
import business.order.entity.TOrderEntity;
import business.order.service.TOrderService;
import com.sys.util.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * FreeOrderController  免单
 * Created by 程洪强 on 2016/8/22.
 */
@Controller
@RequestMapping("/business/freeOrder")
public class FreeOrderController {

    @Resource
    private TOrderService tOrderService;
    @Resource
    private GoodsService goodsService;

    /**
     * 转入页面
     * @return
     */
    @RequestMapping(params = "toList")
    public String toList(HttpServletRequest request){
        request.setAttribute("_modulesLink", ContextHolderUtil.getRequestUrl());
        request.setAttribute("_conditions", ResourceUtil.getParamsMap(request));
        return "business/freeorder/t_order_list";
    }

    /**
     * 获取数据列表
     * @param
     * @return
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public DataGrid datagrid(DataGrid d,HttpServletRequest request){
        if(StringUtils.isBlank(d.getOrders()))
            d.setOrders("_createDate:desc,_updateDate:desc");
        List<Criterion> list= BeanUtil.generateCriterions(TOrderEntity.class, request, false);
        tOrderService.fillDataGrid(TOrderEntity.class, list, d);
        return d;
    }

    /**
     * 免单
     * @param id
     * @return
     */
    @RequestMapping(params = "exempt")
    @ResponseBody
    public SuccessMsg exempt(String id){
        SuccessMsg msg = new SuccessMsg();
        TOrderEntity entity = this.tOrderService.getEntity(TOrderEntity.class,id);
        //减去商家佣金
        if(entity.getDjstate() == 0){  //订单状态位下单，减去下单佣金
            this.goodsService.addShopCommission(entity.getGoodnum(),-entity.getCommissionXd(),1);
        }else if(entity.getDjstate() == 1 && entity.getSclx().equals("0")){  //已收货状态且收菜类型为任务收菜，减去下单佣金和收货佣金
            float commission = entity.getCommissionXd()+entity.getCommissionSh();
            this.goodsService.addShopCommission(entity.getGoodnum(),-commission,1);
        }else if(entity.getDjstate() == 2 && entity.getSclx().equals("0")){  //已评价状态且收菜类型为任务收菜，减去下单、收货和评价佣金
            float commission = entity.getCommissionXd()+entity.getCommissionSh()+entity.getCommissionPj()+entity.getCommissionShPj();
            this.goodsService.addShopCommission(entity.getGoodnum(),-commission,1);
        }else if(entity.getDjstate() == 3){ //以免单
            msg.setSuccess(false);
            msg.setMsg("该订单已经免单");
            return msg;
        }
        //订单标记为免单
        entity.setDjstate(3);
        this.tOrderService.update(entity);
        msg.setSuccess(true);
        msg.setMsg("免单成功");
        return msg;
    }
}
