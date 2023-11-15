package dev.capstone.repository.memory;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryShoppingCart {

    /*
        @Component에 의해서 Spring이 Bean을 등록할 때 자동으로 싱글톤 객체를 만들어서 스프링 컨테이너에서 관리함.
        따라서 개발자가 굳이 싱글톤 패턴을 코드 상에 적용할 필요가 없음!
     */

    // static final을 통해 클래스 내부에서 자신의 싱글톤 인스턴스를 생성함
//    private static final MemoryShoppingCart instance = new MemoryShoppingCart();

    private Map<String, Map<String, Integer>> shoppingCart = new HashMap<>();  // <token 정보, 주문 정보>


    // 오직 이 메소드를 통해서만 인스턴스를 호출할 수 있음
//    public static MemoryShoppingCart getInstance() {
//        return instance;
//    }

    // private로 외부에서 생성자를 통한 인스턴스 생성이 불가능하게 막아버림
    // private MemoryShoppingCart() {}

    public void removeByToken(String token) {
        shoppingCart.remove(token);
    }

    public void put(String token, Map<String, Integer> requestedInfo) {
        shoppingCart.put(token, requestedInfo);
    }

    public Map<String, Integer> getByToken(String token) {
        return shoppingCart.get(token);
    }

    public Map<String, Map<String, Integer>> findAll() {
        return shoppingCart;
    }
}
