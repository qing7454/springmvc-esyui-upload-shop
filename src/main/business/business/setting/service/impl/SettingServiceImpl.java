package business.setting.service.impl;

import business.setting.service.SettingService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("settingService")
@Transactional
public class SettingServiceImpl extends CommonService implements SettingService {

}
