package business.bankcard.service.impl;

import business.bankcard.service.BankcardService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("bankcardService")
@Transactional
public class BankcardServiceImpl extends CommonService implements BankcardService {

}
