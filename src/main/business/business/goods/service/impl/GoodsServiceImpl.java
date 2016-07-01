package business.goods.service.impl;

import business.goods.entity.GoodsEntity;
import business.goods.service.GoodsService;
import business.shop.entity.ShopEntity;
import com.sys.service.impl.CommonService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl extends CommonService implements GoodsService {

    /**
     * 根据SKU查询佣金
     * @param sku   SKU编号
     * @param type  查询佣金类型，0：刷单-PC，1:刷单-APP，2：收货且评价，3：收货，4：评价
     * @return
     */
    @Override
    public float getCommissionBySKU(String sku, int type) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("goodid",sku));
        List<GoodsEntity> goods = this.getDataList(GoodsEntity.class,list,null);
        if(goods.size()>0){
            ShopEntity shop = this.getEntity(ShopEntity.class,goods.get(0).getShopid());
            if(type == 0){
                return shop.getSdPc();
            }else if(type == 1){
                return shop.getSdApp();
            }else if(type == 2){
                return shop.getShPj();
            }else if(type == 3){
                return shop.getSh();
            }else if(type == 4){
                return shop.getPj();
            }
        }
        return 0;
    }
}
