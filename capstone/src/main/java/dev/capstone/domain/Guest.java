package dev.capstone.domain;

import dev.capstone.domain.enumerated.YesOrNo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Integer id;

     // 지금 외래키 설정이 안 되어 있음.
    @ManyToOne
    @JoinColumn(name ="market_id", nullable = false)
    private Market market;

    @Column(nullable = false)
    private Integer number_of_people;
    private String details;

    @Column(nullable = false, name = "token_str")
    private String token;

    @Enumerated(EnumType.STRING)
    private YesOrNo waiting;

    @Enumerated(EnumType.STRING)
    @Column(name = "approved_payment")
    private YesOrNo approved;

    @Column(name = "table_number")
    private Integer table;

    @Column(name = "guest_number", nullable = false)
    private Integer gnumber;

    @PrePersist
    public void prePersist(){
        this.waiting = this.waiting == null ? YesOrNo.valueOf("Yes") : this.waiting;
        this.approved = this.approved == null ? YesOrNo.valueOf("No") : this.approved;
        this.details = this.details.equals("") ? null : this.details;
    }
}
