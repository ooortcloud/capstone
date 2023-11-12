package dev.capstone.repository;

import dev.capstone.domain.MainUser;
import dev.capstone.dto.ChangeMainUserDTO;
import dev.capstone.repository.querydsl.MainUserQueryRepository;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest
public class MainUserRepositoryTest {

    private final MainUserRepository mainUserRepository;
    private final MainUserQueryRepository mainUserQueryRepository;

    @Autowired
    public MainUserRepositoryTest(MainUserRepository mainUserRepository, MainUserQueryRepository mainUserQueryRepository) {
        this.mainUserRepository = mainUserRepository;
        this.mainUserQueryRepository = mainUserQueryRepository;
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
    void updateUserTest() {
        MainUser mainUser = new MainUser(null, "test", "1234", "홍길동", "장막시티", null);
        mainUserRepository.save(mainUser);

        ChangeMainUserDTO dto = new ChangeMainUserDTO("test", "5678", "홍길동", "축복시티");
        mainUserQueryRepository.updateMainUser(dto);

        assertThat(mainUserRepository.findById(mainUser.getId()).orElseThrow().getUserpw()).isEqualTo("5678");
        assertThat(mainUserRepository.findById(mainUser.getId()).orElseThrow().getUserresidence()).isEqualTo("축복시티");
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

        mainUserRepository.deleteById(mainUser.getId());

        assertThat(mainUserRepository.findAll()).isEqualTo(test);
    }

    // ========================================================================
}
