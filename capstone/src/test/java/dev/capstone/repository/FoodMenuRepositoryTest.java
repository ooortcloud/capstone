package dev.capstone.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.domain.jointable.MenuReview;
import dev.capstone.dto.FoodMenuDTO;
import dev.capstone.repository.querydsl.FoodMenuQueryRepository;
import dev.capstone.repository.springdatajpa.FoodMenuRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    void saveTest() throws JsonProcessingException {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "네모시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "세모시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));
        String asString = objectMapper.writeValueAsString(myOption);

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu);

        assertThat(foodMenuRepository.findAll().get(0)).isEqualTo(foodMenu);
    }

    // ========================================================================

    @Test
    @DisplayName("매장 아이디로 특정 매장의 전체 메뉴 조회 - SpringDataJPA")
    // @Commit
    void findAllByMarketIdTest() throws JsonProcessingException {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "네모시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "세모시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));
        String asString = objectMapper.writeValueAsString(myOption);

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu);
        FoodMenu foodMenu2 = new FoodMenu(null, market, null, Level.Recommend, "", "국물떡볶이", "추천 메뉴~!", 7000f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu2);

        assertThat(foodMenuRepository.findAllByMarketId(marketRepository.findByMarketname("이모네떡볶이집").get(0).getId()))
                .isEqualTo(Arrays.asList(foodMenu, foodMenu2));
    }

    @Test
    @DisplayName("options를 String에서 Map으로 변환하기")
    void testStringToMap() throws JsonProcessingException {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "네모시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집 1호점", "세모시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));
        String asString = objectMapper.writeValueAsString(myOption);
        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu);

        List<FoodMenu> foundMenus = foodMenuRepository.findAllByMarketId(marketRepository.findByMarketname("이모네떡볶이집 1호점").get(0).getId());

        String getOptions = foundMenus.get(0).getOptions();

        Map<String, Object> stringToMap = stringToMap(getOptions);
        assertThat(stringToMap.get("맵기 정도")).usingRecursiveComparison()
                .isEqualTo(Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));

    }


    // ========================================================================

    @Test
    @DisplayName("메뉴 수정")
    // 오직 `field/property 'market' differ` 에러만 발생하면 테스트 성공
    void updateMenuTest() throws JsonProcessingException {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "축복시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));
        String asString = objectMapper.writeValueAsString(myOption);
        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu);

        myOption.put("곱빼기 유무", Arrays.asList(YesOrNo.Yes.toString(), YesOrNo.No.toString()));
        asString = objectMapper.writeValueAsString(myOption);
        FoodMenuDTO foodMenuDTO = new FoodMenuDTO(foodMenuRepository.findByName("치즈떡볶이").getId(), Level.Recommend.toString(), "더블치즈떡볶이",
                "이번에 새롭게 리뉴얼했습니다!", 10000f, 1000f, asString);
        foodMenuQueryRepository.updateMenu(foodMenuDTO);

        List<MenuReview> emptyList = new ArrayList<>();
        assertThat(foodMenuRepository.findAll().get(0))
                .usingRecursiveComparison().isEqualTo(new FoodMenu(foodMenuRepository.findAll().get(0).getId(), market, emptyList, Level.Recommend, "", "더블치즈떡볶이"
                        , "이번에 새롭게 리뉴얼했습니다!", 10000f, 1000f, YesOrNo.Yes, asString));
    }

    // ========================================================================

    @Test
    @DisplayName("메뉴 삭제")
    void foodMenuDeleteTest() throws JsonProcessingException {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "네모시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "이모네떡볶이집", "세모시티");
        marketRepository.save(market);
        Map<String, Object> myOption = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        myOption.put("맵기 정도", Arrays.asList("순한맛", "중간맛", "매운맛", "아주 매운맛"));
        String asString = objectMapper.writeValueAsString(myOption);

        FoodMenu foodMenu = new FoodMenu(null, market, null, Level.Best, "", "치즈떡볶이", "우리집 대표 메뉴입니다~!", 7500f,
                0f, YesOrNo.Yes, asString);
        foodMenuRepository.save(foodMenu);

        foodMenuRepository.deleteByName(foodMenu.getName());

        assertThat(foodMenuRepository.findAll()).isEmpty();
    }

    // ========================================================================

    private Map<String, Object> stringToMap(String longText) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> returnMap = objectMapper.readValue(longText, Map.class);
        return returnMap;
    }
}
