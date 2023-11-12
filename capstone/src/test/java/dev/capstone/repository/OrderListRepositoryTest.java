package dev.capstone.repository;

import dev.capstone.domain.Guest;
import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.repository.querydsl.GuestQueryRepository;
import dev.capstone.repository.springdatajpa.GuestRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import dev.capstone.repository.springdatajpa.OrderListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class OrderListRepositoryTest {

    private final MainUserRepository mainUserRepository;
    private final MarketRepository marketRepository;
    private final GuestRepository guestRepository;
    private final GuestQueryRepository guestQueryRepository;
    private final OrderListRepository orderListRepository;

    @Autowired
    public OrderListRepositoryTest(MainUserRepository mainUserRepository, MarketRepository marketRepository,
                                   GuestRepository guestRepository, GuestQueryRepository guestQueryRepository,
                                   OrderListRepository orderListRepository) {
        this.mainUserRepository = mainUserRepository;
        this.marketRepository = marketRepository;
        this.guestRepository = guestRepository;
        this.guestQueryRepository = guestQueryRepository;
        this.orderListRepository = orderListRepository;
    }

    // ========================================================================

    @Test
    @DisplayName("주문 등록")
    void createOrderTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO(1, "없습니다.",
                marketRepository.findByMarketname("홍콩반점").get(0).getId());
        mappingHelper(guestWaitingDTO, guest, guest.getToken());
        guestRepository.save(guest);

        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("핫도그", 3);
        orderMap.put("떡볶이", 2);

        OrderList orderList = new OrderList(null, guestRepository.findByGnumber(1), market, orderMap);
        orderListRepository.save(orderList);

        assertThat(orderListRepository.findAll().get(0)).isEqualTo(orderList);
    }


    // ========================================================================

    @Test
    @DisplayName("전체 주문 목록 조회")
    void findAllOrderList() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest1 = new Guest();
        String token1 = UUID.randomUUID().toString();
        guest1.setToken(token1);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO(1, "없습니다.",
                marketRepository.findByMarketname("홍콩반점").get(0).getId());
        mappingHelper(guestWaitingDTO, guest1, guest1.getToken());
        guestRepository.save(guest1);

        Guest guest2 = new Guest();
        String token2 = UUID.randomUUID().toString();
        guest2.setToken(token2);
        GuestWaitingDTO guestWaitingDTO2 = new GuestWaitingDTO(2, "커플이요.",
                marketRepository.findByMarketname("홍콩반점").get(0).getId());
        mappingHelper(guestWaitingDTO2, guest2, guest2.getToken());
        guestRepository.save(guest2);

        Map<String, Integer> orderMap1 = new HashMap<>();
        orderMap1.put("핫도그", 3);
        orderMap1.put("떡볶이", 2);
        Map<String, Integer> orderMap2 = new HashMap<>();
        orderMap1.put("핫도그", 1);
        orderMap1.put("콜라", 1);

        OrderList orderList1 = new OrderList(null, guest1, market, orderMap1);
        OrderList orderList2 = new OrderList(null, guest2, market, orderMap2);
        orderListRepository.save(orderList1);
        orderListRepository.save(orderList2);
        List<OrderList> test = new ArrayList<>();
        test.add(orderList1);
        test.add(orderList2);

        assertThat(orderListRepository.findAll()).isEqualTo(test);
    }

    // ========================================================================

    @Test
    @DisplayName("주문 처리 || 주문 거부")
    void approvedOrderTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);
        Market market = new Market(null, YesOrNo.No, mainUser, null, null, null, "홍콩반점", "장막시티");
        marketRepository.save(market);

        Guest guest = new Guest();
        String token = UUID.randomUUID().toString();
        guest.setToken(token);
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO(1, "없습니다.",
                marketRepository.findByMarketname("홍콩반점").get(0).getId());
        mappingHelper(guestWaitingDTO, guest, guest.getToken());
        guestRepository.save(guest);

        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("핫도그", 3);
        orderMap.put("떡볶이", 2);

        OrderList orderList = new OrderList(null, guestRepository.findByGnumber(1), market, orderMap);
        orderListRepository.save(orderList);

        orderListRepository.deleteByGuestGnumber(guest.getGnumber());
        List<OrderList> test = new ArrayList<>();

        assertThat(orderListRepository.findAll()).isEqualTo(test);
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
