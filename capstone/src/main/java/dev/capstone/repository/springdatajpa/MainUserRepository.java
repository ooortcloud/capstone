package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainUserRepository extends JpaRepository<MainUser, Integer> {

    List<MainUser> findAllByName(String name);

}
