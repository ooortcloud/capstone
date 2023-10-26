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
import dev.capstone.repository.memory.MemoryDeleteGuestTokenList;
import dev.capstone.repository.springdatajpa.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    private final MarketQueryRepository marketQueryRepository;
    private final GuestRepository guestRepository;
    private final GuestQueryRepository guestQueryRepository;
    private final MemoryDeleteGuestTokenList deleteGuestTokenList;
    private final OrderListRepository orderListRepository;
    private final OrderListQueryRepository orderListQueryRepository;


    // ========================================================================

    // 매장 등록
    public Market save(Market market) {
        return marketRepository.save(market);
    }

    // ========================================================================

    // 전체 매장 조회
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    // 이름으로 매장 조회
    public List<Market> findByMarketName(String marketName) {
        return marketRepository.findByName(marketName);
    }

    // 현재 이용 중인 guest 전체 조회
    public List<Guest> findAllGuest() {
        return guestRepository.findAll();
    }
    
    // 현재 이용 중인 guest들을 guest id로 조회
    public Optional<Guest> findGuestByGuestId(Integer guestId) {
        return guestRepository.findById(guestId);
    }
    
    // 전체 주문 목록 조회
    public List<OrderList> findAllOrderList() {
        return orderListRepository.findAll();
    }

    //
    

    // ========================================================================

    // 인테이블 처리 -> validation 처리 필요
    public void inTable(String token, Integer table) {
        guestQueryRepository.inTable(token, table);
    }
    
    // 정산 처리 = guest 제거 대상
    public void updateDeleteId(String token, Integer table) {
        guestQueryRepository.updateApprovedByToken(token, table);
        deleteGuestTokenList.add(token);
    }
    
    // ========================================================================

    // 이용 종료한 고객을 DB 상에서 한 번에 제거 -> 스케줄링 전략을 사용
    @Scheduled(cron = "0/20 * * * * *")
    public void deleteGuestsByIds() {
        guestQueryRepository.deleteAllByApproved();
    }

    // 예외 : 정상적으로 제거되지 못하고 리스트가 초기화된 경우 -> DB 상에서 12시간 이상 잔류된 데이터를 알아서 삭제하도록 하거나 그런 코드를 만들자.

    // 주문 처리
    public void approvedOrder(Integer guestNumber) {
        orderListRepository.deleteByGuestGnumber(guestNumber);
        // 사용자에게 승인되었음을 알림
    }

    // ========================================================================

    // 주문 거부
    public void rejectOrder(GuestCookieDTO guestCookieDTO) {
        orderListRepository.deleteByGuestGnumber(guestCookieDTO.getGuest_number());
        // 사용자에게 거부되었음을 알림
    }

}
