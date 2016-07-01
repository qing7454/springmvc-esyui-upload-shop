package business.order.service.impl;

import business.order.service.TOrderService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("tOrderService")
@Transactional
public class TOrderServiceImpl extends CommonService implements TOrderService {

}
