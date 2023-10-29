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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        // assertThat(guestRepository.findByGnumber(1).getId()).isEqualTo(null);

        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("핫도그", 3);
        orderMap.put("떡볶이", 2);
        // guestRepository.findByGnumber(1)

        OrderList orderList = new OrderList(null, market, orderMap);
        orderListRepository.save(orderList);

        assertThat(orderListRepository.findAll().get(0)).isEqualTo(orderList);
    }


    // ========================================================================

    @Test
    @DisplayName("전체 주문 목록 조회")
    void findAllOrderList() {

    }

    // ========================================================================


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
