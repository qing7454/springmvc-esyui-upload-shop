package business.order.service;

import com.sys.service.ICommonService;
public interface TOrderService  extends ICommonService {

    /**
     * ����Ƿ���ڶ���
     * @param order
     * @return
     */
    boolean checkOrder(String order);
}
