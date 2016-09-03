package business.finance.debt.service.impl;

import business.finance.debt.service.DebtService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("debtService")
@Transactional
public class DebtServiceImpl extends CommonService implements DebtService {

}
