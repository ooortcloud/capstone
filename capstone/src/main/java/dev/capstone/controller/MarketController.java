package dev.capstone.controller;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.dto.FoodMenuSaveDTO;
import dev.capstone.service.FoodMenuService;
import dev.capstone.service.GuestService;
import dev.capstone.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;
    private final FoodMenuService foodMenuService;


    // ========================================================================

    // 매장 등록
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Market market) {
        Market savedMarket = marketService.save(market);
        log.info(String.valueOf(savedMarket));
        return ResponseEntity.ok("200 OK");
    }

    // 메뉴 추가
    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@RequestBody FoodMenuSaveDTO requestedInfo) {
        FoodMenu savedMenu = foodMenuService.save(mappingHelper(requestedInfo));
        log.info(String.valueOf(savedMenu));
        return ResponseEntity.ok("200 OK");
    }
    

    // ========================================================================

    // 이름으로 매장 조회
    @GetMapping("/findByMarketName")
    public ResponseEntity<String> findByMarketName(@RequestParam String marketName) {
        List<Market> markets = marketService.findByMarketName(marketName);
        log.info(markets.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 현재 이용 중인 guest 전체 조회
    @GetMapping("/findAllGuest")
    public ResponseEntity<String> findAllGuest() {
        List<Guest> allGuest = marketService.findAllGuest();
        log.info(allGuest.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 현재 이용 중인 guest들을 이름으로 조회
    @GetMapping("/findGuestByGuestId")
    public ResponseEntity<String> findGuestByGuestId(@RequestParam("id") Integer id) {
        marketService.findGuestByGuestId(id);
        return ResponseEntity.ok("200 OK");
    }

    // 전체 주문 목록 조회

    // 

    // ========================================================================

    // 정산 처리 -> 서버 상에서 매 시간마다 랜덤 토큰을 만들어서 요청값을 대조시킬 예정 -> validation은 로그인 세션 관리 단계에서 진행
    @PostMapping("/approvedPayment")
    public ResponseEntity<String> updateDeleteId(@RequestParam("token") String token, @RequestParam("table") Integer table) {
        marketService.updateDeleteId(token, table);

        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================

    // 주문 처리
    



    // ========================================================================

    // 주문 거부



    private FoodMenu mappingHelper(FoodMenuSaveDTO requestedInfo) {
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setMenu_type(Level.valueOf(requestedInfo.getMenu_type()));
        foodMenu.setMenu_name(requestedInfo.getMenu_name());
        foodMenu.setMenu_description(requestedInfo.getMenu_description());
        foodMenu.setPrice(requestedInfo.getPrice());
        foodMenu.setOptions(requestedInfo.getOptions());

        Market market = new Market();
        market.setId(requestedInfo.getMarket_id());
        foodMenu.setMarket(market);

        return foodMenu;
    }
}
