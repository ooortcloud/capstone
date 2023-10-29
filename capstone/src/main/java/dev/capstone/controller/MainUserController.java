package dev.capstone.controller;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.service.MainUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/mainuser")
@RequiredArgsConstructor
public class MainUserController {

    private final MainUserService mainUserService;

    // ========================================================================

    // 회원가입
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MainUser mainUser) {
        MainUser savedUser = mainUserService.save(mainUser);
        log.info(String.valueOf(savedUser));
        return ResponseEntity.ok("200 OK");
    }

    // 매장 등록
    @PostMapping("/addMarket")
    public ResponseEntity<String> addMarket(@RequestBody Market market) {
        Market savedMarket = mainUserService.addMarket(market);
        log.info(String.valueOf(savedMarket));
        return ResponseEntity.ok("200 OK");
    }

    // ========================================================================



    // ========================================================================

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam("id") Integer id, @RequestBody MainUser mainUser) {
        mainUserService.update(id, mainUser);
        return ResponseEntity.ok("200 OK");
    }


    // ========================================================================

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id) {
        mainUserService.deleteById(id);
        return ResponseEntity.ok("200 OK");
    }

}
