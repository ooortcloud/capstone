package dev.capstone.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.enumerated.Level;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static dev.capstone.domain.QFoodMenu.*;

@Repository
@Transactional
public class FoodMenuQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public FoodMenuQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

    // 특정 타입의 메뉴만 조회
    public List<FoodMenu> findMenuByLevel(Integer marketId, String menuType) {
        return query
                .selectFrom(foodMenu)
                .where(equalByMenuType(marketId, menuType))
                .fetch();
    }

    private BooleanExpression equalByMenuType(Integer marketId, String menuType) {
        if(StringUtils.hasText(menuType)) {
            return foodMenu.market.id.eq(marketId)
                    .and(foodMenu.menu_type.eq(Level.valueOf(menuType)));
        }
        return null;
    }



}
