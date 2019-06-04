package lk.ijse.pos.dao;

import lk.ijse.pos.entity.CustomEntity;

import java.util.List;

public interface QueryDAO {

    List<CustomEntity> getOrdersTotal() throws Exception;

}
