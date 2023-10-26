package dev.capstone.repository.springdatajpa;

import dev.capstone.domain.FoodReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReviewRepository extends JpaRepository<FoodReview, Integer> {
}
