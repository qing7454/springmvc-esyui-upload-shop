package business.finance.commission.service;

import business.finance.commission.entity.CommissionEntity;
import com.sys.service.ICommonService;

import java.util.Date;
import java.util.List;

/**
 * CommissionService
 * Created by �̺�ǿ on 2016/8/29.
 */
public interface CommissionService extends ICommonService {

    /**
     * Ӷ��ͳ��
     * @param shopName ������
     * @param startDate ��ʼʱ��
     * @param endDate  ����ʱ��
     * @return
     */
    List<CommissionEntity> getCommissionStatistics(List<String> shopName,Date startDate,Date endDate);


    /**
     * ����ʱ��β����ʱ������н��׵ĵ�����
     * @param startDate
     * @param endDate
     * @return
     */
    List getShopsByDate(Date startDate,Date endDate);
}
