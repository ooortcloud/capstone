package dev.capstone.repository.memory;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryDeleteGuestTokenList {

    private List<String> deleteGuestTokenList = new ArrayList<>();

    public void add(String token) {
        deleteGuestTokenList.add(token);
    }
}
