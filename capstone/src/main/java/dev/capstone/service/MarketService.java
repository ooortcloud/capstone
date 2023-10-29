package dev.capstone.service;

import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.repository.querydsl.GuestQueryRepository;
import dev.capstone.repository.querydsl.MarketQueryRepository;
import dev.capstone.repository.querydsl.OrderListQueryRepository;
import dev.capstone.repository.springdatajpa.GuestRepository;
import dev.capstone.repository.springdatajpa.MarketRepository;
import dev.capstone.repository.springdatajpa.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    private final MarketQueryRepository marketQueryRepository;
    private final GuestRepository guestRepository;
    private final GuestQueryRepository guestQueryRepository;
    private final OrderListRepository orderListRepository;
    private final OrderListQueryRepository orderListQueryRepository;


    // ========================================================================

    // 매장 등록
    public Market save(Market market) {
        return marketRepository.save(market);
    }

    // ========================================================================

    // 내가 보유한 매장 조희
    public List<Market> findAllByUser_id(Integer userId) {
        return marketQueryRepository.findMyMarkets(userId);
    }

    // 현재 내 가게를 이용 중인 guest 전체 조회
    public List<Guest> findAllGuest() {
        return guestRepository.findAll();
    }
    
    // 현재 내 가게를 이용 중인 특정 guest를 gnumber로 조회
    public Guest findGuestByGnumber(Integer gnumber) {
        return guestRepository.findByGnumber(gnumber);
    }
    
    // 전체 주문 목록 조회
    public List<OrderList> findAllOrderList() {
        return orderListRepository.findAll();
    }

    //
    

    // ========================================================================

    // 인테이블 처리 -> validation 처리 필요
    public void inTable(String token, Integer tableNum) {
        guestQueryRepository.inTable(token, tableNum);
    }
    
    // 정산 처리 = guest 제거 대상
    public void updateApprovedByGnumber(Integer gnumber) {
        guestQueryRepository.updateApprovedByGnumber(gnumber);
    }
    
    // ========================================================================

    // 이용 종료한 고객을 DB 상에서 한 번에 제거 -> 스케줄링 전략을 사용
    // 무조건 public이어야 함.
    @Scheduled(cron = "0/20 * * * * *")
    public void deleteAllByApproved() {
        guestQueryRepository.deleteAllByApproved();
    }

    // 주문 처리
    public void approvedOrder(Integer guestNumber) {
        orderListRepository.deleteByGuestGnumber(guestNumber);
        // 사용자에게 승인되었음을 알림
    }

    // ========================================================================

    // 주문 거부
    public void rejectOrder(GuestCookieDTO guestCookieDTO) {
        orderListRepository.deleteByGuestGnumber(guestCookieDTO.getGnumber());
        // 사용자에게 거부되었음을 알림
    }

}
