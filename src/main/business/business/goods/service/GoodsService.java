package business.goods.service;

import com.sys.service.ICommonService;
public interface GoodsService  extends ICommonService {

    /**
     * ����SKU��ѯӶ��
     * @param sku   SKU���
     * @param type  ��ѯӶ�����ͣ�0��ˢ��-PC��1:ˢ��-APP��2���ջ������ۣ�3���ջ���4������
     * @return
     */
   float getCommissionBySKU(String sku,int type);


    /**
     * �����̻��Ļ������Ӷ��
     * @param sku   SKU����
     * @param money   ���
     * @param type   ���ͣ�0�����1��Ӷ��
     * @return
     */
    boolean addShopCommission(String sku,float money,int type);
}
