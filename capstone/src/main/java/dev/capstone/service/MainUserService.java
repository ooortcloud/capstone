package dev.capstone.service;

import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.querydsl.MainUserQueryRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MainUserService {

    private final MainUserRepository mainUserRepository;
    private final MainUserQueryRepository mainUserQueryRepository;
    private final MarketRepository marketRepository;


    // ========================================================================

    // 회원가입
    public MainUser save(MainUser mainUser) {
        return mainUserRepository.save(mainUser);
    }

    // 매장 등록
    public Market addMarket(Market market) {
        return marketRepository.save(market);
    }


    // ========================================================================



    // ========================================================================

    // 계정 수정
    public void update(Integer requestedId, MainUser requestedData) {
         MainUser oldData = mainUserRepository.findById(requestedId).orElseThrow();
         oldData.setUserpw(requestedData.getUserpw());
         oldData.setUsername(requestedData.getUsername());
         oldData.setUserresidence(requestedData.getUserresidence());
    }

    // ========================================================================

    // 계정 삭제
    public void deleteById(Integer id) {
        mainUserRepository.deleteById(id);
    }

}
