package lk.ijse.pos.dao;

import lk.ijse.pos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    //int getLastOrderId()throws Exception;
    Order getTopOrdersByOrderByIdDesc() throws Exception;
}
