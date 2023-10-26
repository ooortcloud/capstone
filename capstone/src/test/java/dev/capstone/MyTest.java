package dev.capstone;

import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.repository.springdatajpa.GuestRepository;
import dev.capstone.repository.springdatajpa.OrderListRepository;
import dev.capstone.service.GuestService;
import dev.capstone.service.MarketService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
public class MyTest {

    @Autowired
    GuestService guestService;
    @Autowired
    GuestRepository guestRepository;
    @Autowired
    MarketService marketService;

    @Test
    @DisplayName("주문 생성 및 주문 처리")
    // 로직 : "게스트 생성 -> 장바구니 추가 -> 주문 -> 주문 처리"
    void test1() {

        // action
        GuestWaitingDTO guestWaitingDTO = new GuestWaitingDTO();
        guestWaitingDTO.setNumberOfPeople(2);
        guestWaitingDTO.setDetails("커플입니다.");
        GuestCookieDTO guestCookieDTO = guestService.createGuest(guestWaitingDTO);

        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("핫도그", 3);
        orderMap.put("떡볶이", 2);
        guestService.putShoppingCart(guestCookieDTO.getToken(), orderMap);

        Market market = new Market();
        market.setId(1);
        Market myMarket = marketService.save(market);
        OrderList orderList = new OrderList();
        Guest guest = guestRepository.findByGuestId(guestCookieDTO.getGuest_number());
        orderList.setGuest(guest);
        orderList.setMarket(myMarket);
        guestService.createOrder(orderList);

        // assertThat()

        marketService.approvedOrder(guestCookieDTO.getGuest_number());

        // check

    }

}
