package dev.capstone.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.domain.Market;
import dev.capstone.domain.enumerated.YesOrNo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dev.capstone.domain.QMarket.*;

@Repository
@Transactional
public class MarketQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MarketQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

    public void certifiedSucceed(Integer marketId) {
            query
                .update(market)
                .where(market.id.eq(marketId))
                    .set(market.certified, YesOrNo.Yes)
                    .execute();

            em.clear();
            em.flush();
    }

    public List<Market> findMyMarkets(Integer userId) {
        return query.selectFrom(market)
                .where(market.mainUser.user_id.eq(userId))
                .fetch();
    }
}
