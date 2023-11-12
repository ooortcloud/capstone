package dev.capstone.controller;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.dto.FoodMenuDTO;
import dev.capstone.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;


    // ========================================================================



    // 메뉴 추가
    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@RequestBody FoodMenuDTO requestedInfo) {
        FoodMenu savedMenu = marketService.addMenu(mappingHelper(requestedInfo));
        log.info(String.valueOf(savedMenu));
        return ResponseEntity.ok("200 OK");
    }
    

    // ========================================================================

    // 내가 보유한 매장 조희
    @GetMapping("/findAllByUser_id")
    public ResponseEntity<String> findAllByUser_id(@RequestParam Integer userId) {
        List<Market> markets = marketService.findAllByUser_id(userId);
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
    @GetMapping("/findGuestByGnumber")
    public ResponseEntity<String> findGuestByGnumber(@RequestParam("gnumber") Integer gnumber) {
        marketService.findGuestByGnumber(gnumber);
        return ResponseEntity.ok("200 OK");
    }

    // 전체 주문 목록 조회

    // 

    // ========================================================================

    // 정산 처리 -> 서버 상에서 매 시간마다 랜덤 토큰을 만들어서 요청값을 대조시킬 예정 -> validation은 로그인 세션 관리 단계에서 진행
    @PostMapping("/approvedPayment")
    public ResponseEntity<String> updateApprovedByGnumber(@RequestParam("gnumber") Integer gnumber) {
        marketService.updateApprovedByGnumber(gnumber);

        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================

    // 주문 처리
    



    // ========================================================================

    // 주문 거부



    private FoodMenu mappingHelper(FoodMenuDTO requestedInfo) {
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setType(Level.valueOf(requestedInfo.getMenu_type()));
        foodMenu.setName(requestedInfo.getMenu_name());
        foodMenu.setDescription(requestedInfo.getMenu_description());
        foodMenu.setPrice(requestedInfo.getPrice());
        foodMenu.setOptions(requestedInfo.getOptions());

        Market market = new Market();
        market.setId(requestedInfo.getMarket_id());
        foodMenu.setMarket(market);

        return foodMenu;
    }
}
