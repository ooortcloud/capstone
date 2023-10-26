package dev.capstone.repository.memory;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryShoppingCart {

    private Map<String, Map<String, Integer>> shoppingCart = new HashMap<>();  // <token 정보, 주문 정보>


    public void remove(String token) {
        shoppingCart.remove(token);
    }

    public void put(String token, Map<String, Integer> requestedInfo) {
        shoppingCart.put(token, requestedInfo);
    }

    public Map<String, Integer> get(String id) {
        return shoppingCart.get(id);
    }
}
