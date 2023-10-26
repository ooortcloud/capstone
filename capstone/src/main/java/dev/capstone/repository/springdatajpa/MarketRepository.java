package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Integer> {

    List<Market> findByName(String marketName);

    List<Market> findByCertified(YesOrNo yesOrNo);

}
