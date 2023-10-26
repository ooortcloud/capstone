package dev.capstone.repository.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryDeleteGuestTokenList {

    private List<String> deleteGuestTokenList = new ArrayList<>();

    public void add(String token) {
        deleteGuestTokenList.add(token);
    }
}
