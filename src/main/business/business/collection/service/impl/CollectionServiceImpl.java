package business.collection.service.impl;

import business.collection.service.CollectionService;
import com.sys.service.impl.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("collectionService")
@Transactional
public class CollectionServiceImpl extends CommonService implements CollectionService {

}
