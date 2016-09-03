package business.finance.commission.service;

import business.finance.commission.entity.CommissionEntity;
import com.sys.service.ICommonService;

import java.util.Date;
import java.util.List;

/**
 * CommissionService
 * Created by 程洪强 on 2016/8/29.
 */
public interface CommissionService extends ICommonService {

    /**
     * 佣金统计
     * @param shopName 店铺名
     * @param startDate 开始时间
     * @param endDate  结束时间
     * @return
     */
    List<CommissionEntity> getCommissionStatistics(List<String> shopName,Date startDate,Date endDate);


    /**
     * 根据时间段查出该时间段内有交易的店铺名
     * @param startDate
     * @param endDate
     * @return
     */
    List getShopsByDate(Date startDate,Date endDate);
}
