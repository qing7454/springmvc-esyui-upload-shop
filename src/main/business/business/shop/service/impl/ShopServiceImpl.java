package business.shop.service.impl;

import business.shop.service.ShopService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("shopService")
@Transactional
public class ShopServiceImpl extends CommonService implements ShopService {

}
