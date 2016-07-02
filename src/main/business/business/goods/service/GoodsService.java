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


    /**
     * 增加商户的货款或者佣金
     * @param sku   SKU编码
     * @param money   金额
     * @param type   类型，0：货款，1：佣金。
     * @return
     */
    boolean addShopCommission(String sku,float money,int type);
}
