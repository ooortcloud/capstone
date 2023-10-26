package dev.capstone.service;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.dto.FoodMenuSaveDTO;
import dev.capstone.repository.querydsl.FoodMenuQueryRepository;
import dev.capstone.repository.springdatajpa.FoodMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodMenuService {

    private final FoodMenuRepository foodMenuRepository;
    private final FoodMenuQueryRepository foodMenuQueryRepository;

    // ========================================================================

    // 메뉴 추가
    public FoodMenu save(FoodMenu foodMenu) {
        return foodMenuRepository.save(foodMenu);
    }


    // ========================================================================

    // 특정 매장의 전체 메뉴 조회 (단, 베스트/추천 메뉴 구분 표시 요구됨)
    public List<FoodMenu> findAllByMarketId(Integer marketId) {
        return foodMenuRepository.findAllByMarketId(marketId);
    }

    // Best 또는 Recommend 메뉴만 조희
    public List<FoodMenu> findByLevel(Integer marketId, String level) {
        return foodMenuQueryRepository.findMenuByLevel(marketId, level);
    }

    // ========================================================================



    // ========================================================================

}
