package dev.capstone.service;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.FoodMenuDTO;
import dev.capstone.dto.GuestCookieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketService {

    private final FacadeService facadeService;


    // ========================================================================

    // 메뉴 추가
    public FoodMenu addMenu(FoodMenu foodMenu) {
        return facadeService.addMenu(foodMenu);
    }

    // ========================================================================

    // 내가 보유한 매장 조희
    public List<Market> findAllMarketByUserid(Integer userId) {
        return facadeService.findAllMarketByUserid(userId);
    }

    // 현재 내 가게를 이용 중인 guest 전체 조회
    public List<Guest> findAllGuest() {
        return facadeService.findAllGuest();
    }
    
    // 현재 내 가게를 이용 중인 특정 guest를 gnumber로 조회
    public Guest findGuestByGnumber(Integer gnumber) {
        return facadeService.findGuestByGnumber(gnumber);
    }
    
    // 전체 주문 목록 조회
    public List<OrderList> findAllOrderList() {
        return facadeService.findAllOrderList();
    }

    // 매장 아이디로 특정 매장의 전체 메뉴 조회 (단, 베스트/추천 메뉴 구분 표시 요구됨)
    public List<FoodMenu> findAllByMarketId(Integer marketId) {
        return facadeService.findAllByMarketId(marketId);
    }

    // 특정 매장의 Best 또는 Recommend 메뉴만 조희
    public List<FoodMenu> findByLevel(Integer marketId, String level) {
        return facadeService.findMenuByLevel(marketId, level);
    }
    

    // ========================================================================

    
    // 정산 처리 = guest 제거 대상
    public void updateApprovedByGnumber(Integer gnumber) {
        facadeService.updateApprovedByGnumber(gnumber);
    }

    // 메뉴 수정
    public void updateMenu(FoodMenuDTO dto) {
        facadeService.updateMenu(dto);
    }
    
    // ========================================================================

    // 이용 종료한 고객을 DB 상에서 한 번에 제거 -> 스케줄링 전략을 사용
    // 무조건 public이어야 함.
    @Scheduled(cron = "0/20 * * * * *")
    public void deleteAllGuestByApproved() {
        facadeService.deleteAllGuestByApproved();
    }

    // 주문 처리 또는 주문 거부
    public void approvedOrder(Integer guestNumber) {
        facadeService.approvedOrder(guestNumber);
        // 사용자에게 승인되었음을 알림 기능 추가
    }

    // 주문 거부
    public void rejectOrder(GuestCookieDTO guestCookieDTO) {
        facadeService.rejectOrder(guestCookieDTO);
        // 사용자에게 거부되었음을 알림 기능 추가
    }

    
    // ========================================================================


}
