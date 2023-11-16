package dev.capstone.service;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainUserService {

    private final FacadeService facadeService;


    // ========================================================================

    // 회원가입
    public MainUser save(MainUser mainUser) {
        return facadeService.saveUser(mainUser);
    }

    // 매장 등록
    public Market addMarket(Market market) {
        return facadeService.addMarket(market);
    }


    // ========================================================================



    // ========================================================================

    // 계정 수정
    public void update(Integer requestedId, MainUser requestedData) {
         facadeService.updateUser(requestedId, requestedData);
    }

    // ========================================================================

    // 계정 삭제
    public void deleteById(Integer id) {
        facadeService.deleteUser(id);
    }

}
