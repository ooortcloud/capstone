package dev.capstone.repository;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.repository.querydsl.MarketQueryRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class MarketRepositoryTest {

    private final MainUserRepository mainUserRepository;
    private final MarketRepository marketRepository;
    private final MarketQueryRepository marketQueryRepository;

    @Autowired
    public MarketRepositoryTest(MainUserRepository mainUserRepository,
                                MarketRepository marketRepository, MarketQueryRepository marketQueryRepository) {
        this.mainUserRepository = mainUserRepository;
        this.marketRepository = marketRepository;
        this.marketQueryRepository = marketQueryRepository;
    }

    // ========================================================================

    @Test
    @DisplayName("매장 등록 & 전체 매장 조회")  // 나중에 점주 이름으로 조회 따로 테스트 해봐야 함.
    void createMarket() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, "보영만두", "축복시티");
        marketRepository.save(market);
        Market market2 = new Market(null, YesOrNo.No, mainUser, null, null, "상남자치킨", "축복시티");
        marketRepository.save(market2);
        List<Market> test = new ArrayList<>();
        test.add(market);
        test.add(market2);

        assertThat(marketRepository.findAll()).isEqualTo(test);
    }

    // ========================================================================

    @Test
    @DisplayName("가게 이름으로 매장 조회")
    void findByNameTest(){
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, "보영만두", "축복시티");
        marketRepository.save(market);
        Market market2 = new Market(null, YesOrNo.No, mainUser, null, null, "상남자치킨", "축복시티");
        marketRepository.save(market2);

        assertThat(marketRepository.findByName("보영만두").get(0)).isEqualTo(market);
    }

    @Test
    @DisplayName("내 가게 모두 조희")
    void findAllByUser_idTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, "보영만두", "축복시티");
        marketRepository.save(market);
        Market market2 = new Market(null, YesOrNo.No, mainUser, null, null, "상남자치킨", "축복시티");
        marketRepository.save(market2);
        List<Market> test = new ArrayList<>();
        test.add(market);
        test.add(market2);

        assertThat(marketQueryRepository.findMyMarkets(mainUserRepository.findAllByName("홍길동").get(0).getUser_id()))
                .isEqualTo(test);
    }

    @Test
    @DisplayName("미인증된 매장 조회")
    void findByCertifiedTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, "보영만두", "축복시티");
        marketRepository.save(market);
        Market market2 = new Market(null, YesOrNo.No, mainUser, null, null, "상남자치킨", "축복시티");
        marketRepository.save(market2);
        List<Market> test = new ArrayList<>();
        test.add(market);
        test.add(market2);

        assertThat(marketRepository.findByCertified(YesOrNo.No)).isEqualTo(test);
    }

    // ========================================================================

    @Test
    @DisplayName("인증 속성 변경")
    void certifiedSucceedTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, "보영만두", "축복시티");
        marketRepository.save(market);

        marketQueryRepository.certifiedSucceed(marketRepository.findByName("보영만두").get(0).getId());

        assertThat(marketRepository.findByName("보영만두").get(0).getCertified()).isEqualTo(YesOrNo.Yes);
    }

    // ========================================================================

    // ========================================================================



}
