package dev.capstone.repository;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.repository.querydsl.FoodMenuQueryRepository;
import dev.capstone.repository.springdatajpa.FoodMenuRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class FoodMenuRepositoryTest {
    private final MainUserRepository mainUserRepository;
    private final MarketRepository marketRepository;
    private final FoodMenuRepository foodMenuRepository;
    private final FoodMenuQueryRepository foodMenuQueryRepository;

    @Autowired
    public FoodMenuRepositoryTest(MainUserRepository mainUserRepository, MarketRepository marketRepository,
                                  FoodMenuRepository foodMenuRepository, FoodMenuQueryRepository foodMenuQueryRepository) {
        this.mainUserRepository = mainUserRepository;
        this.marketRepository = marketRepository;
        this.foodMenuRepository = foodMenuRepository;
        this.foodMenuQueryRepository = foodMenuQueryRepository;
    }


    // ========================================================================

    @Test
    @DisplayName("메뉴 추가")
    void saveTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "축복시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, myOption);
        foodMenuRepository.save(foodMenu);

        assertThat(foodMenuRepository.findAll().get(0)).isEqualTo(foodMenu);
    }

    // ========================================================================

    @Test
    @DisplayName("매장 아이디로 특정 매장의 전체 메뉴 조회")
    void findAllByMarketIdTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "축복시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, myOption);
        foodMenuRepository.save(foodMenu);
        FoodMenu foodMenu2 = new FoodMenu(null, market, null, Level.Recommend, "", "국물떡볶이", "추천 메뉴~!", 7000f,
                0f, YesOrNo.Yes, myOption);
        foodMenuRepository.save(foodMenu2);

        assertThat(foodMenuRepository.findAllByMarketId(marketRepository.findAll().get(0).getId()))
                .isEqualTo(Arrays.asList(foodMenu, foodMenu2));

    }

    @Test
    @DisplayName("특정 매장의 메뉴를 레벨 별로 조회")
    void findByLevel(){
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "축복시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, myOption);
        foodMenuRepository.save(foodMenu);
        FoodMenu foodMenu2 = new FoodMenu(null, market, null, Level.Recommend, "", "국물떡볶이", "추천 메뉴~!", 7000f,
                0f, YesOrNo.Yes, myOption);
        foodMenuRepository.save(foodMenu2);

        assertThat(foodMenuQueryRepository.findMenuByLevel(marketRepository.findAll().get(0).getId(), "Best"))
                .isEqualTo(Arrays.asList(foodMenu));
    }

    // ========================================================================



    // ========================================================================



    // ========================================================================


}
