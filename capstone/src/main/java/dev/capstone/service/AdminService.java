package dev.capstone.service;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.repository.querydsl.MarketQueryRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final MainUserRepository mainUserRepository;
    private final MarketRepository marketRepository;
    private final MarketQueryRepository marketQueryRepository;

    // ========================================================================


    // ========================================================================

    // 전체 계정 조회
    public List<MainUser> findAllUsers() {
        return mainUserRepository.findAll();
    }

    // 점주 이름으로 계정 조회
    public List<MainUser> findAllByName(String name) {
        return mainUserRepository.findAllByUsername(name);
    }

    // 전체 매장 조회
    public List<Market> findAllMarkets() {
        return marketRepository.findAll();
    }

    // 미인증된 매장 조회
    public List<Market> findByCertified(YesOrNo yesOrNo) {
        return marketRepository.findByCertified(yesOrNo);
    }

    // ========================================================================

    // 미인증 -> 인증 속성 변경
    public void certifiedSucceed(Integer marketId) {
        marketQueryRepository.certifiedSucceed(marketId);
    }

    // ========================================================================
}
