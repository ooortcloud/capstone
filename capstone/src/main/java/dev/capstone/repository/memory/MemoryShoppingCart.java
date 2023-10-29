package dev.capstone.repository.memory;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryShoppingCart {

    private Map<String, Map<String, Integer>> shoppingCart = new HashMap<>();  // <token 정보, 주문 정보>


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
