package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.jointable.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    void deleteByGuestGnumber(Integer guestNumber);

}
