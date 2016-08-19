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
     * ����SKU��ѯӶ��
     * @param sku   SKU���
     * @param type  ��ѯӶ�����ͣ�0��ˢ��-PC��1:ˢ��-APP��2���ջ������ۣ�3���ջ���4������
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

    /**
     * �����̻��Ļ������Ӷ��
     * @param sku   SKU����
     * @param money   ���
     * @param type   ���ͣ�0�����1��Ӷ��
     * @return
     */
    @Override
    public boolean addShopCommission(String sku, float money, int type) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("goodid",sku));
        List<GoodsEntity> goods = this.getDataList(GoodsEntity.class,list,null);
        if(goods.size()>0){
            ShopEntity shop = this.getEntity(ShopEntity.class,goods.get(0).getShopid());
            if(type == 0){
                shop.setPayment(shop.getPayment()+money);
            }else if(type == 1){
                shop.setCommission(shop.getCommission()+money);
            }
            if(this.update(shop)){
                return true;
            }
        }
        return false;
    }
    /**
     * ��ȡ�̼��ղ�����
     * @param sku
     * @return
     */
    @Override
    public Integer getShopSclx(String sku) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("goodid",sku));
        List<GoodsEntity> goods = this.getDataList(GoodsEntity.class,list,null);
        if(goods.size()>0){
            ShopEntity shop = this.getEntity(ShopEntity.class,goods.get(0).getShopid());

            if(shop != null){
                return shop.getScyq();
            }
        }
        return null;
    }

    @Override
    public boolean checkSKU(String sku) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("goodid",sku));
        List<GoodsEntity> goods = this.getDataList(GoodsEntity.class,list,null);
        if(goods.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public String getShopNameBySKU(String sku) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("goodid",sku));
        List<GoodsEntity> goods = this.getDataList(GoodsEntity.class,list,null);
        if(goods.size()>0){
            ShopEntity shop = this.getEntity(ShopEntity.class,goods.get(0).getShopid());

            if(shop != null){
                return shop.getName();
            }
        }
        return null;
    }
}
