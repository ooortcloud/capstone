package dev.capstone.domain.jointable;

import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.FoodReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// JOIN 엔티티
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuReview_id;

    @ManyToOne(fetch = FetchType.LAZY)  // 양방향(주인)
    @JoinColumn(name = "menu_id")
    private FoodMenu foodMenu;

    @ManyToOne(fetch = FetchType.LAZY)  // 양방향(주인)
    @JoinColumn(name = "review_id")
    private FoodReview foodReview;
}
