package business.order.service.impl;

import business.order.entity.TOrderEntity;
import business.order.service.TOrderService;
import com.sys.service.impl.CommonService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.List;

@Service("tOrderService")
@Transactional
public class TOrderServiceImpl extends CommonService implements TOrderService {
    @Override
    public boolean checkOrder(String order) {
        List<Criterion> list = new ArrayList<>();
        list.add(Restrictions.eq("ddnum",order));
        List<TOrderEntity> orders = this.getDataList(TOrderEntity.class,list,null);
        if(orders.size() > 0){
            return true;
        }
        return false;
    }
}
