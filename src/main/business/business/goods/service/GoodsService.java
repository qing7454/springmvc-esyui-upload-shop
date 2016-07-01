package business.goods.service;

import com.sys.service.ICommonService;
public interface GoodsService  extends ICommonService {

    /**
     * 根据SKU查询佣金
     * @param sku   SKU编号
     * @param type  查询佣金类型，0：刷单-PC，1:刷单-APP，2：收货且评价，3：收货，4：评价
     * @return
     */
   float getCommissionBySKU(String sku,int type);
}
