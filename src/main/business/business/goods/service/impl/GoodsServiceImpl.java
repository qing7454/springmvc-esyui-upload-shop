package business.goods.service.impl;

import business.goods.service.GoodsService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("goodsService")
@Transactional
public class GoodsServiceImpl extends CommonService implements GoodsService {

}
