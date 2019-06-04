package lk.ijse.pos.dao;

import lk.ijse.pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<Item,String> {
    
}
