package dev.capstone.controller;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.dto.MarketUpdateDTO;
import dev.capstone.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // ========================================================================



    // ========================================================================

    // 전체 계정 조회
    @GetMapping("/findAllUsers")
    public ResponseEntity<String> findAllUsers() {
        List<MainUser> all = adminService.findAllUsers();
        log.info(all.toString());
        return ResponseEntity.ok("200 OK");
    }

    // 이름으로 계정 조회
    @GetMapping("/findAllByName")
    public ResponseEntity<String> findAllByName(@RequestParam("name") String name) {
        List<MainUser> foundUsers = adminService.findAllByName(name);
        log.info(String.valueOf(foundUsers));
        return ResponseEntity.ok("200 OK");
    }

    // 인증 || 미인증된 매장 조회
    @GetMapping("/findByCertified")
    public ResponseEntity<String> findByCertified(@RequestParam("yn") YesOrNo yesOrNo) {
        List<Market> markets = adminService.findByCertified(yesOrNo);
        log.info(markets.toString());
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================

    // 미인증 -> 인증 매장으로 변경
    @PostMapping("/updateCertified")
    public ResponseEntity<String> updateCertified(@RequestBody MarketUpdateDTO marketUpdateDTO) {
        adminService.certifiedSucceed(marketUpdateDTO.getMarketId());
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================



    // ========================================================================



}
