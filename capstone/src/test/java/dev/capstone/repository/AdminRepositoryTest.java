package dev.capstone.repository;

import dev.capstone.repository.springdatajpa.MainUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class AdminRepositoryTest {

    private final MainUserRepository mainUserRepository;

    public AdminRepositoryTest(MainUserRepository mainUserRepository) {
        this.mainUserRepository = mainUserRepository;
    }

    @Test
    @DisplayName("전체 계정 조회")
    void test1() {
        // when


        // given

        // then
    }

}
