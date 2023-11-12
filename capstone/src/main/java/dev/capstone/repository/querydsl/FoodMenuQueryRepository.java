package dev.capstone.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.domain.FoodMenu;
import dev.capstone.domain.enumerated.Level;
import dev.capstone.dto.FoodMenuDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

import static dev.capstone.domain.QFoodMenu.*;

@Repository
public class FoodMenuQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public FoodMenuQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

    // ========================================================================


    // ========================================================================

    // 특정 타입의 메뉴만 조회
    public List<FoodMenu> findMenuByLevel(Integer marketId, String menuType) {
        return query
                .selectFrom(foodMenu)
                .where(equalByMenuType(marketId, menuType))
                .fetch();
    }

    // ========================================================================

    // 이미지 변경은 나중에 처리할 예정.
    public void updateMenu(FoodMenuDTO dto) {
        query.update(foodMenu)
                .set(Collections.singletonList(foodMenu.type), Collections.singletonList(Level.valueOf(dto.getMenu_type())))
                .set(foodMenu.name, dto.getMenu_name())
                .set(foodMenu.description, dto.getMenu_description())
                .set(foodMenu.price, dto.getPrice())
                .set(foodMenu.discount_price, dto.getDiscount_price())
                .set(foodMenu.options, dto.getOptions())
                .where(foodMenu.id.eq(dto.getMarket_id()))
                .execute();

        em.flush();
        em.clear();
    }

    // ========================================================================
    private BooleanExpression equalByMenuType(Integer marketId, String menuType) {
        if(StringUtils.hasText(menuType)) {
            return foodMenu.market.id.eq(marketId)
                    .and(foodMenu.type.eq(Level.valueOf(menuType)));
        }
        return null;
    }



}
