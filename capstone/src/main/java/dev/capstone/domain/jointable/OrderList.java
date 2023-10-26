package dev.capstone.domain.jointable;

import dev.capstone.domain.Guest;
import dev.capstone.domain.Market;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderList_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "guest_number", nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    @Type(JsonType.class)
    @Column(nullable = false, columnDefinition = "longtext")
    private Map<String, Integer> order_map;

}
