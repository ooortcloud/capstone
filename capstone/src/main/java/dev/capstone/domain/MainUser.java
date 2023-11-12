package dev.capstone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// @Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class MainUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "user_id")
    private String userid;

    @Column(nullable = false, name = "user_pw")
    private String userpw;

    @Column(nullable = false, name = "user_name")
    private String username;

    @Column(nullable = false, name = "user_residence")
    private String userresidence;

    @OneToMany(mappedBy = "mainUser")  // 양방향(노예)
    private List<Market> markets = new ArrayList<>();
}
