package dev.capstone.service;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.Guest;
import dev.capstone.domain.MainUser;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.domain.jointable.OrderList;
import dev.capstone.dto.FoodMenuDTO;
import dev.capstone.dto.GuestCookieDTO;
import dev.capstone.dto.GuestWaitingDTO;
import dev.capstone.repository.memory.MemoryShoppingCart;
import dev.capstone.repository.querydsl.*;
import dev.capstone.repository.springdatajpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional  // JPA의 손쉬운 update flush를 위해 적용
public class FacadeService {  // facade 디자인 패턴 적용

    private final MemoryShoppingCart memoryShoppingCart;
    private final FoodMenuQueryRepository foodMenuQueryRepository;
    private final GuestQueryRepository guestQueryRepository;
    private final MainUserQueryRepository mainUserQueryRepository;
    private final MarketQueryRepository marketQueryRepository;
    private final OrderListQueryRepository orderListQueryRepository;
    private final FoodMenuRepository foodMenuRepository;
    private final GuestRepository guestRepository;
    private final MainUserRepository mainUserRepository;
    private final MarketRepository marketRepository;
    private final OrderListRepository orderListRepository;

    // =======================================================================

    // 회원가입
    public MainUser saveUser(MainUser mainUser) {
        return mainUserRepository.save(mainUser);
    }

    // 매장 등록
    public Market addMarket(Market market) {
        return marketRepository.save(market);
    }

    // 게스트 생성
    public GuestCookieDTO createGuest(GuestWaitingDTO guestWaitingDTO) {
        Guest guest = new Guest();
        UUID uuid = UUID.randomUUID();

        GuestCookieDTO guestCookieDTO = mappingHelper(guestWaitingDTO, guest, uuid);

        guestRepository.save(guest);

        return guestCookieDTO;
    }

    // 장바구니에 담기(수정하는 경우에도 그냥 이거로 덮어씌우자.)
    public Map<String, Integer> putShoppingCart(String token, Map<String, Integer> requestedInfo) {
        memoryShoppingCart.removeByToken(token);
        memoryShoppingCart.put(token, requestedInfo);

        return requestedInfo;
    }

    // 주문 등록
    public void createOrder(OrderList orderListInfo) {
        orderListRepository.save(orderListInfo);
    }

    // 메뉴 추가
    public FoodMenu addMenu(FoodMenu foodMenu) {
        return foodMenuRepository.save(foodMenu);
    }

    // =======================================================================

    // 장바구니 조회
    public Map<String, Integer> getShoppingCart(String id) {
        Map<String, Integer> sendList = memoryShoppingCart.getByToken(id);
        return sendList;
    }

    // 전체 계정 조회
    public List<MainUser> findAllUsers() {
        return mainUserRepository.findAll();
    }

    // 점주 이름으로 계정 조회
    public List<MainUser> findAllUserByUsername(String name) {
        return mainUserRepository.findAllByUsername(name);
    }

    // 전체 매장 조회
    public List<Market> findAllMarkets() {
        return marketRepository.findAll();
    }

    // 미인증된 매장 조회
    public List<Market> findByCertifiedMarket(YesOrNo yesOrNo) {
        return marketRepository.findByCertified(yesOrNo);
    }

    // 내가 보유한 매장 조희
    public List<Market> findAllMarketByUserid(Integer userId) {
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

    // 매장 아이디로 특정 매장의 전체 메뉴 조회 (단, 베스트/추천 메뉴 구분 표시 요구됨)
    public List<FoodMenu> findAllByMarketId(Integer marketId) {
        return foodMenuRepository.findAllByMarketId(marketId);
    }

    // 특정 매장의 Best 또는 Recommend 메뉴만 조희
    public List<FoodMenu> findMenuByLevel(Integer marketId, String level) {
        return foodMenuQueryRepository.findMenuByLevel(marketId, level);
    }

    // =======================================================================

    // 계정 수정
    public void updateUser(Integer requestedId, MainUser requestedData) {
        MainUser oldData = mainUserRepository.findById(requestedId).orElseThrow();
        oldData.setUserpw(requestedData.getUserpw());
        oldData.setUsername(requestedData.getUsername());
        oldData.setUserresidence(requestedData.getUserresidence());
    }

    // 인테이블 처리 -> validation 처리 필요
    public void inTable(String token, Integer tableNum) {
        guestQueryRepository.inTable(token, tableNum);
    }

    // 미인증 -> 인증 속성 변경
    public void certifiedSucceed(Integer marketId) {
        marketQueryRepository.certifiedSucceed(marketId);
    }

    // 정산 처리 = guest 제거 대상
    public void updateApprovedByGnumber(Integer gnumber) {
        guestQueryRepository.updateApprovedByGnumber(gnumber);
    }

    // 메뉴 수정
    public void updateMenu(FoodMenuDTO dto) {
        foodMenuQueryRepository.updateMenu(dto);
    }

    // =======================================================================

    // 계정 삭제
    public void deleteUser(Integer id) {
        mainUserRepository.deleteById(id);
    }

    // 이용 종료한 고객을 DB 상에서 한 번에 제거 -> 스케줄링 전략을 사용
    // 무조건 public이어야 함.
    @Scheduled(cron = "0/20 * * * * *")
    public void deleteAllGuestByApproved() {
        guestQueryRepository.deleteAllByApproved();
    }

    // 주문 처리 또는 주문 거부
    public void approvedOrder(Integer guestNumber) {
        orderListRepository.deleteByGuestGnumber(guestNumber);
        // 사용자에게 승인되었음을 알림 기능 추가
    }

    // 주문 거부
    public void rejectOrder(GuestCookieDTO guestCookieDTO) {
        orderListRepository.deleteByGuestGnumber(guestCookieDTO.getGnumber());
        // 사용자에게 거부되었음을 알림 기능 추가
    }

    // =======================================================================

    private GuestCookieDTO mappingHelper(GuestWaitingDTO guestWaitingDTO, Guest guest, UUID uuid) {
        Market market = new Market();
        market.setId(guestWaitingDTO.getMarket_id());

        guest.setToken(String.valueOf(uuid));
        guest.setNumber_of_people(guestWaitingDTO.getNumberOfPeople());
        guest.setDetails(guestWaitingDTO.getDetails());
        guest.setMarket(market);
        Integer n = guestQueryRepository.findHighestNumberOfGnumber(guestWaitingDTO.getMarket_id()) + 1;
        guest.setGnumber(n);

        GuestCookieDTO guestCookieDTO = new GuestCookieDTO(String.valueOf(uuid), n);
        return guestCookieDTO;
    }
}
