package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.QueryDAO;
import lk.ijse.pos.entity.CustomEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomEntity> getOrdersTotal() throws Exception {

        List<Object[]> list = entityManager.createNativeQuery("SELECT id, SUM(qty * unitPrice) AS Total FROM `Order` INNER JOIN\n" +
                "  OrderDetail OD on `Order`.id = OD.orderId GROUP BY id").getResultList();

        List<CustomEntity> al = new ArrayList<>();

        for (Object[] objects : list) {
            al.add(new CustomEntity((Integer) objects[0], (Double) objects[1]));
        }

        return al;
    }
}
