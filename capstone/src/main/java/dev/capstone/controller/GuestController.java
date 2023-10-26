package dev.capstone.controller;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.service.FoodMenuService;
import dev.capstone.service.GuestService;
import dev.capstone.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/guest")
public class GuestController {  // 가장 Validation에 신경써야 하는 컨트롤러

    private final GuestService guestService;
    private final FoodMenuService foodMenuService;
    private final MarketService marketService;

    // ========================================================================

    // 게스트 생성 -> 나중에 갱신되는 QR 코드와 대조해서 유효성 검사하는 코드 추가해야 함.
    @PostMapping("/create")
    public ResponseEntity<String> createGuest(@RequestBody GuestWaitingDTO guestWaitingDTO) {
        GuestCookieDTO guestCookieDTO = guestService.createGuest(guestWaitingDTO);
        log.info(String.valueOf(guestCookieDTO));
        return ResponseEntity.ok("200 OK");
    }

    // 장바구니 추가 || 변경
    @PostMapping("/putShoppingCart")
    public ResponseEntity<String> putShoppingCart(@RequestBody Map<String, Integer> requestedInfo, @RequestParam("token") String token) {
        Map<String, Integer> cart = guestService.putShoppingCart(token, requestedInfo);// JSON은 기본적으로 Map 타입이다.
        log.info(cart.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 주문 넣기
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody OrderList orderListInfo) {
        guestService.createOrder(orderListInfo);
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================

    // 특정 매장의 전체 메뉴 조회
    @GetMapping("/findAll")
    public ResponseEntity<String> findByMarketId(@RequestParam("id") Integer marketId) {
        List<FoodMenu> menus = foodMenuService.findAllByMarketId(marketId);
        log.info(menus.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 등급별 메뉴 조회
    @GetMapping("/findByLevel")
    public ResponseEntity<String> findByLevel(@RequestParam("id") Integer marketId, @RequestParam("level") String level) {
        List<FoodMenu> menus = foodMenuService.findByLevel(marketId, level);
        log.info(menus.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 장바구니 조회
    @GetMapping("/getShoppingCart")
    public ResponseEntity<String> getShoppingCart(@RequestParam("token") String token) {
        Map<String, Integer> shoppingCart = guestService.getShoppingCart(token);
        log.info(shoppingCart.toString());
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================

    // 인테이블 처리 -> 역시 Validation 관련 문제가 있음. Table에 직접적으로 찍었을 때만 해당 요청이 유효하도록 만들어야 함.
    @PostMapping("/inTable")
    public ResponseEntity<String> inTable(@RequestParam("token") String token, @RequestParam("table") Integer table) {
        marketService.inTable(token, table);
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================


    // ========================================================================
}
