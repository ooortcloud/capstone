package dev.capstone.domain;

import dev.capstone.domain.enumerated.YesOrNo;
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
public class MainUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false, name = "user_name")
    private String name;

    @Column(nullable = false)
    private String user_residence;

    @OneToMany(mappedBy = "mainUser")
    private List<Market> markets = new ArrayList<>();
}
