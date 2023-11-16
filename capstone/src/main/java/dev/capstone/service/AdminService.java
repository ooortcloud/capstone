package dev.capstone.service;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final FacadeService facadeService;

    // ========================================================================


    // ========================================================================

    // 전체 계정 조회
    public List<MainUser> findAllUsers() {
        return facadeService.findAllUsers();
    }

    // 점주 이름으로 계정 조회
    public List<MainUser> findAllByUsername(String name) {
        return facadeService.findAllUserByUsername(name);
    }

    // 전체 매장 조회
    public List<Market> findAllMarkets() {
        return facadeService.findAllMarkets();
    }

    // 미인증된 매장 조회
    public List<Market> findByCertifiedMarket(YesOrNo yesOrNo) {
        return facadeService.findByCertifiedMarket(yesOrNo);
    }

    // ========================================================================

    // 미인증 -> 인증 속성 변경
    public void certifiedSucceed(Integer marketId) {
        facadeService.certifiedSucceed(marketId);
    }

    // ========================================================================
}
