package dev.capstone.domain;

import dev.capstone.domain.enumerated.YesOrNo;
import dev.capstone.domain.jointable.OrderList;
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
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)  // 양방향(주인)
    @JoinColumn(name ="market_id", nullable = false)
    private Market market;

    @OneToOne(mappedBy = "guest")  // 양방향(노예)
    private OrderList orderList;

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
    private Integer tableNum;

    @Column(name ="guest_number", nullable = false)
    private Integer gnumber;  // 토큰 말고 가게에서 실제 발급받은 대기 번호

    // commit 직전에 초기값 설정
    @PrePersist
    public void prePersist(){
        this.waiting = this.waiting == null ? YesOrNo.valueOf("Yes") : this.waiting;
        this.approved = this.approved == null ? YesOrNo.valueOf("No") : this.approved;
        this.details = this.details.equals("") ? null : this.details;
        this.gnumber = this.gnumber == null ? 0 : this.gnumber;
    }
}
