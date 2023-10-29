package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

    Guest findByToken(String token);

    Guest findByGnumber(Integer gnumber);
}
