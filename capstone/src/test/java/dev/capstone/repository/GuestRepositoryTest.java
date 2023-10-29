package dev.capstone.repository;

import dev.capstone.domain.Guest;
import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.repository.memory.MemoryShoppingCart;
import dev.capstone.repository.querydsl.GuestQueryRepository;
import dev.capstone.repository.springdatajpa.GuestRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

// @EntityScan(basePackageClasses = {})
// @Import(ConfigTests.class)
@Transactional
@SpringBootTest
public class GuestRepositoryTest {

    private final GuestQueryRepository guestQueryRepository;
    private final GuestRepository guestRepository;
    private final MemoryShoppingCart memoryShoppingCart;
    private final MarketRepository marketRepository;
    private final MainUserRepository mainUserRepository;

    @Autowired
    public GuestRepositoryTest(GuestQueryRepository guestQueryRepository,
                               GuestRepository guestRepository, MemoryShoppingCart memoryShoppingCart,
                               MarketRepository marketRepository, MainUserRepository mainUserRepository) {
        this.guestQueryRepository = guestQueryRepository;
        this.guestRepository = guestRepository;
        this.memoryShoppingCart = memoryShoppingCart;
        this.marketRepository = marketRepository;
        this.mainUserRepository = mainUserRepository;
    }


    // ========================================================================

    @Test
    @DisplayName("웨이팅 단계에서 게스트 생성")
    void createGuestTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(1);
        guestWaitingDTO.setMarket_id(marketRepository.findByName("홍콩반점").get(0).getId());
        guestWaitingDTO.setDetails("없습니다.");

        mappingHelper(guestWaitingDTO, guest, guest.getToken());

        guestRepository.save(guest);

        assertThat(guestRepository.findByToken(token)).isEqualTo(guest);
    }

    @Test
    @DisplayName("MemoryShoppingCart 객체에 값을 넣고 조회되는지 테스트")
    void putShoppingCartTest() {
        String token = String.valueOf(UUID.randomUUID());
        Map<String, Integer> requestedInfo = new HashMap<>();

        requestedInfo.put("김치볶음밥", 2);
        requestedInfo.put("핫도그", 3);

        memoryShoppingCart.put(token, requestedInfo);

        assertThat(memoryShoppingCart.getByToken(token)).isEqualTo(requestedInfo);
    }

    // ========================================================================

    @Test
    @DisplayName("현재 이용 중인 guest 전체 조회")
    void findAllGuestTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(1);
        guestWaitingDTO.setMarket_id(marketRepository.findByName("홍콩반점").get(0).getId());
        guestWaitingDTO.setDetails("없습니다.");

        mappingHelper(guestWaitingDTO, guest, guest.getToken());

        guestRepository.save(guest);

        MainUser mainUser2 = new MainUser(null, "qwer", "5678", "이아리", "축복시티", null);
        mainUserRepository.save(mainUser2);
        Market market2 = new Market(null, YesOrNo.No, mainUser2, null, "불맛나는떡볶이", "축복시티");
        marketRepository.save(market2);

        Guest guest2 = new Guest();
        String token2 = UUID.randomUUID().toString();
        guest2.setToken(token2);
        GuestWaitingDTO guestWaitingDTO2 = new GuestWaitingDTO();
        guestWaitingDTO2.setNumberOfPeople(3);
        guestWaitingDTO2.setMarket_id(marketRepository.findByName("불맛나는떡볶이").get(0).getId());
        guestWaitingDTO2.setDetails("아이 한 명 있어요!");

        mappingHelper(guestWaitingDTO2, guest2, guest2.getToken());

        guestRepository.save(guest2);

        List<Guest> test = new ArrayList<>();
        test.add(guest);
        test.add(guest2);

        assertThat(guestRepository.findAll()).isEqualTo(test);
    }

    @Test
    @DisplayName("현재 이용 중인 특정 guest를 gnumber로 조회")
    void findByGnumberTest() {
        // 웨이팅 게스트 생성
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(1);
        guestWaitingDTO.setMarket_id(marketRepository.findByName("홍콩반점").get(0).getId());
        guestWaitingDTO.setDetails("없습니다.");

        mappingHelper(guestWaitingDTO, guest, guest.getToken());

        guestRepository.save(guest);

        MainUser mainUser2 = new MainUser(null, "qwer", "5678", "이아리", "축복시티", null);
        mainUserRepository.save(mainUser2);
        Market market2 = new Market(null, YesOrNo.No, mainUser2, null, "불맛나는떡볶이", "축복시티");
        marketRepository.save(market2);

        Guest guest2 = new Guest();
        String token2 = UUID.randomUUID().toString();
        guest2.setToken(token2);
        GuestWaitingDTO guestWaitingDTO2 = new GuestWaitingDTO();
        guestWaitingDTO2.setNumberOfPeople(3);
        guestWaitingDTO2.setMarket_id(marketRepository.findByName("불맛나는떡볶이").get(0).getId());
        guestWaitingDTO2.setDetails("아이 한 명 있어요!");

        mappingHelper(guestWaitingDTO2, guest2, guest2.getToken());

        guestRepository.save(guest2);

        assertThat(guestRepository.findByGnumber(2)).isEqualTo(guest2);
    }

    // ========================================================================

    @Test
    @DisplayName("인테이블 처리")
    void inTableTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(1);
        guestWaitingDTO.setMarket_id(marketRepository.findByName("홍콩반점").get(0).getId());
        guestWaitingDTO.setDetails("없습니다.");

        mappingHelper(guestWaitingDTO, guest, guest.getToken());

        guestRepository.save(guest);

        guestQueryRepository.inTable(token, 6);

        assertThat(guestRepository.findByToken(token).getWaiting()).isEqualTo(YesOrNo.No);
    }

    @Test
    @DisplayName("정산 처리")
    void updateApprovedByTokenTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(1);
        guestWaitingDTO.setMarket_id(marketRepository.findByName("홍콩반점").get(0).getId());
        guestWaitingDTO.setDetails("없습니다.");

        mappingHelper(guestWaitingDTO, guest, guest.getToken());

        guestRepository.save(guest);

        guestQueryRepository.updateApprovedByGnumber(guest.getGnumber());

        assertThat(guestRepository.findByGnumber(guest.getGnumber()).getApproved()).isEqualTo(YesOrNo.Yes);
    }

    // ========================================================================

    // ========================================================================

    private Guest mappingHelper(GuestWaitingDTO guestWaitingDTO, Guest guest, String uuid) {
        Market market = new Market();
        market.setId(guestWaitingDTO.getMarket_id());

        guest.setToken(uuid);
        guest.setNumber_of_people(guestWaitingDTO.getNumberOfPeople());
        guest.setDetails(guestWaitingDTO.getDetails());
        guest.setMarket(market);
        Integer n = guestQueryRepository.findHighestNumberOfGnumber(guestWaitingDTO.getMarket_id()) + 1;
        guest.setGnumber(n);

        return guest;
    }
}
