package dev.capstone.service;

import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.repository.memory.MemoryShoppingCart;
import dev.capstone.repository.querydsl.GuestQueryRepository;
import dev.capstone.repository.springdatajpa.GuestRepository;
import dev.capstone.repository.springdatajpa.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestQueryRepository guestQueryRepository;
    private final OrderListRepository orderListRepository;
    private final MemoryShoppingCart shoppingCart;


    // ========================================================================

    // 게스트 생성
    public GuestCookieDTO createGuest(GuestWaitingDTO guestWaitingDTO) {
        Guest guest = new Guest();
        UUID uuid = UUID.randomUUID();

        GuestCookieDTO guestCookieDTO = mappingHelper(guestWaitingDTO, guest, uuid);

        guestRepository.save(guest);

        return guestCookieDTO;
    }

    // 장바구니에 담기(수정하는 경우에도 그냥 이거로 덮어씌우자.)
    public Map<String, Integer> putShoppingCart(String token, Map<String, Integer> requestedInfo) {
        shoppingCart.removeByToken(token);
        shoppingCart.put(token, requestedInfo);

        return requestedInfo;
    }

    // 주문 등록
    public void createOrder(OrderList orderListInfo) {
        orderListRepository.save(orderListInfo);
    }

    // ========================================================================

    // 장바구니 조회
    public Map<String, Integer> getShoppingCart(String id) {
        Map<String, Integer> sendList = shoppingCart.getByToken(id);
        return sendList;
    }

    // ========================================================================

    // 인테이블 처리 -> validation 처리 필요
    public void inTable(String token, Integer tableNum) {
        guestQueryRepository.inTable(token, tableNum);
    }


    // ========================================================================



    // ========================================================================

    private GuestCookieDTO mappingHelper(GuestWaitingDTO guestWaitingDTO, Guest guest, UUID uuid) {
        Market market = new Market();
        market.setId(guestWaitingDTO.getMarket_id());

        guest.setToken(String.valueOf(uuid));
        guest.setNumber_of_people(guestWaitingDTO.getNumberOfPeople());
        guest.setDetails(guestWaitingDTO.getDetails());
        guest.setMarket(market);
        Integer n = guestQueryRepository.findHighestNumberOfGnumber(guestWaitingDTO.getMarket_id()) + 1;
        guest.setGnumber(n);

        GuestCookieDTO guestCookieDTO = new GuestCookieDTO(String.valueOf(uuid), n);
        return guestCookieDTO;
    }
}
