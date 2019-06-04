package lk.ijse.pos.dao;

import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, OrderDetailPK> {
    
}
