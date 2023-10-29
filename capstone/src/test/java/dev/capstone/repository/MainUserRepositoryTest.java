package dev.capstone.repository;

import dev.capstone.domain.MainUser;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest
public class MainUserRepositoryTest {

    private final MainUserRepository mainUserRepository;

    @Autowired
    public MainUserRepositoryTest(MainUserRepository mainUserRepository) {
        this.mainUserRepository = mainUserRepository;
    }

    // ========================================================================

    @Test
    @DisplayName("회원가입 테스트")
    void createUserTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        MainUser mainUser2 = new MainUser(null, "qwer", "1234", "마리아", "축복시티", null);
        mainUserRepository.save(mainUser);
        mainUserRepository.save(mainUser2);

        List<MainUser> savedUser = new ArrayList<>();
        savedUser.add(mainUser);
        savedUser.add(mainUser2);

        assertThat(mainUserRepository.findAll()).isEqualTo(savedUser);
    }

    // ========================================================================


    // ========================================================================

    @Test
    @DisplayName("계정 수정 테스트")
    @Commit  // 직접 DB에서 확인해야 함.
    void updateUserTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);

        mainUser.setPw("5678");
        mainUser.setName("마리아");
        mainUser.setUser_residence("축복시티");
        // updateUser("5678", "마리아", "축복시티", mainUser.getUser_id());

        // assertThat(mainUserRepository.findById(mainUser.getUser_id()).orElseThrow()).isEqualTo(mainUser);
    }

    // ========================================================================

    @Test
    @DisplayName("계정 삭제 테스트")
    void deleteUserTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        MainUser mainUser2 = new MainUser(null, "qwer", "1234", "마리아", "축복시티", null);
        List<MainUser> test = new ArrayList<>();
        test.add(mainUser2);
        mainUserRepository.save(mainUser);
        mainUserRepository.save(mainUser2);

        mainUserRepository.deleteById(mainUser.getUser_id());

        assertThat(mainUserRepository.findAll()).isEqualTo(test);
    }

    // ========================================================================

    /*
    @Query("update MainUser m set m.pw = :pw, m.name = :name, m.user_residence = :residence where m.user_id = :user_id")
    void updateUser(String pw, String name, String residence, Integer user_id) {}

     */
}
