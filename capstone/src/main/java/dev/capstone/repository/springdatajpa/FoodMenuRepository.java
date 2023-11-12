package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer> {

    // ==================================

    // ==================================
    List<FoodMenu> findAllByMarketId(Integer marketId);

    FoodMenu findByName(String name);

    // ==================================


    // ==================================

    void deleteByName(String name);

}
