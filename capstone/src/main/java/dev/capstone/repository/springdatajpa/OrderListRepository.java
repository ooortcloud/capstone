package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.jointable.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    // void deleteByGuestGnumber(Integer guestNumber);
    /*
    @Query("INSERT INTO order_list (guest_number, market_id, order_map) VALUES (?1, ?2, ?3)")
    void saveOrder(Integer guest_number, Integer market_id, Map<String, Integer> orderMap);
     */


}
