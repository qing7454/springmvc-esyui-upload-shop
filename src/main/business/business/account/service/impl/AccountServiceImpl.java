package business.account.service.impl;

import business.account.service.AccountService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("accountService")
@Transactional
public class AccountServiceImpl extends CommonService implements AccountService {

}
