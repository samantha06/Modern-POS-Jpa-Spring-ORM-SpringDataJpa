package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.OrderBO;
import lk.ijse.pos.dao.CustomerDAO;
import lk.ijse.pos.dao.ItemDAO;
import lk.ijse.pos.dao.OrderDAO;
import lk.ijse.pos.dao.OrderDetailDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Component
@Transactional
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerDAO customerDAO;

    public void placeOrder(OrderDTO order) throws Exception {

        // Find the customer
        Customer customer = customerDAO.getOne(order.getCustomerId());
        // Save the order
        orderDAO.save(new Order(order.getOrderId(), order.getOrderDate(), customer));
        //  Save OrderDetails and Update the Qty.
        for (OrderDetailDTO dto : order.getOrderDetails()) {
            orderDetailDAO.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));
            // Find the item
            Item item = itemDAO.getOne(dto.getItemCode());
            // Calculate the qty. on hand
            int qty = item.getQtyOnHand() - dto.getQty();
            // Update the new qty.on hand
            item.setQtyOnHand(qty);
        }



    }

    public int generateOrderId() throws Exception {
        try {
            return orderDAO.getTopOrdersByOrderByIdDesc().getId() + 1;
        }catch (NoResultException e){
            return 1;
        }
    }

}
