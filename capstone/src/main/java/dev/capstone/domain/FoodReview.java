package dev.capstone.domain;

import dev.capstone.domain.jointable.MenuReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FoodReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @OneToMany(mappedBy = "foodReview")  // 양방향(노예)
    private List<MenuReview> menuReviews = new ArrayList<>();

    @Column(nullable = false)
    private Float stars;

    @Column(nullable = false)
    private String body;
}
