package dev.capstone.service;

import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.dto.GuestWaitingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final FacadeService facadeService;

    // ========================================================================

    // 게스트 생성
    public GuestCookieDTO createGuest(GuestWaitingDTO guestWaitingDTO) {
        return facadeService.createGuest(guestWaitingDTO);
    }

    // 장바구니에 담기(수정하는 경우에도 그냥 이거로 덮어씌우자.)
    public Map<String, Integer> putShoppingCart(String token, Map<String, Integer> requestedInfo) {
        return facadeService.putShoppingCart(token, requestedInfo);
    }

    // 주문 등록
    public void createOrder(OrderList orderListInfo) {
        facadeService.createOrder(orderListInfo);
    }

    // ========================================================================

    // 장바구니 조회
    public Map<String, Integer> getShoppingCart(String id) {
        return facadeService.getShoppingCart(id);
    }

    // ========================================================================

    // 인테이블 처리 -> validation 처리 필요
    public void inTable(String token, Integer tableNum) {
        facadeService.inTable(token, tableNum);
    }


    // ========================================================================

}
