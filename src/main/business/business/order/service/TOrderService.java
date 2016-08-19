package business.order.service;

import com.sys.service.ICommonService;
public interface TOrderService  extends ICommonService {

    /**
     * 检查是否存在订单
     * @param order
     * @return
     */
    boolean checkOrder(String order);
}
