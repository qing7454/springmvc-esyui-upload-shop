package business.finance.commission.service.impl;

import business.finance.commission.entity.CommissionEntity;
import business.finance.commission.service.CommissionService;
import business.order.entity.TOrderEntity;
import com.sys.service.impl.CommonService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * CommissionServiceImpl
 * Created by �̺�ǿ on 2016/8/29.
 */
@Service("commissionService")
@Transactional
public class CommissionServiceImpl extends CommonService implements CommissionService{


    /**
     * ����ʱ��β����ʱ������н��׵ĵ�����
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List getShopsByDate(Date startDate, Date endDate) {
        StringBuffer hql = new StringBuffer();
        Map<String,Object> map = new HashMap<>();
        hql.append(" SELECT DISTINCT shopname FROM TOrderEntity");
        hql.append(" WHERE 1=1 ");
        if(startDate != null){
            hql.append(" AND xdsj >=:startDate");
            map.put("startDate", startDate);
        }
        if(endDate != null){
            hql.append(" AND xdsj <=:endDate");
            map.put("endDate", endDate);
        }
        List list = this.getDataByHql(hql.toString(), map);
        return list;
    }

    /**
     * Ӷ��ͳ��
     * @param shopName ������
     * @param startDate ��ʼʱ��
     * @param endDate  ����ʱ��
     * @return
     */
    @Override
    public List<CommissionEntity> getCommissionStatistics(List<String> shopName, Date startDate, Date endDate) {
        List<CommissionEntity> entities = new ArrayList<>();
        //ѭ����Ҫ��ѯ�ĵ�����
        for(String shop:shopName){
            CommissionEntity entity = new CommissionEntity();
            List<Criterion> list = new ArrayList<>();
            list.add(Restrictions.eq("shopname", shop));
            if(startDate != null){
                list.add(Restrictions.ge("xdsj", startDate));
            }
            if(endDate != null){
                list.add(Restrictions.le("xdsj",endDate));
            }
            List<TOrderEntity> orders = this.getDataList(TOrderEntity.class,list,null);  //��ѯ�õ����ڸ�ʱ����﷢���Ķ���
            float xd = 0f;
            float shpj = 0f;
            float sh = 0f;
            float pj = 0f;
            for (TOrderEntity order :orders){    //ѭ���������ѽ�����
                xd = xd +order.getCommissionXd();
                shpj = shpj + order.getCommissionShPj();
                sh = sh + order.getCommissionSh();
                pj = pj + order.getCommissionPj();
            }
            entity.setShopName(shop);
            entity.setCommissionXd(xd);
            entity.setCommissionShPj(shpj);
            entity.setCommissionSh(sh);
            entity.setCommissionPj(pj);
            entity.setAmount(xd+shpj+sh+pj);
            entities.add(entity);
        }
        return entities;
    }
}
