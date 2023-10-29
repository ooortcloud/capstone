package dev.capstone.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.domain.jointable.QOrderList;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static dev.capstone.domain.jointable.QOrderList.orderList;

@Repository
public class OrderListQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public OrderListQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

}
