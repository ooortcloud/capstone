package dev.capstone.service;

import dev.capstone.domain.MainUser;
import dev.capstone.repository.springdatajpa.MainUserRepository;
import dev.capstone.repository.querydsl.MainUserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MainUserService {

    private final MainUserRepository mainUserRepository;
    private final MainUserQueryRepository mainUserQueryRepository;


    // ========================================================================

    // 회원가입
    public MainUser save(MainUser mainUser) {
        return mainUserRepository.save(mainUser);
    }


    // ========================================================================



    // ========================================================================

    // 계정 수정
    public void update(Integer requestedId, MainUser requestedData) {
         MainUser oldData = mainUserRepository.findById(requestedId).orElseThrow();
         oldData.setPw(requestedData.getPw());
         oldData.setName(requestedData.getName());
         oldData.setUser_residence(requestedData.getUser_residence());
    }

    // ========================================================================

    // 계정 삭제
    public void deleteById(Integer id) {
        mainUserRepository.deleteById(id);
    }

}
