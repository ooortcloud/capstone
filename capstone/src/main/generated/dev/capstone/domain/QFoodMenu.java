package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import dev.capstone.domain.jointable.QMenuReview;


/**
 * QFoodMenu is a Querydsl query type for FoodMenu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodMenu extends EntityPathBase<FoodMenu> {

    private static final long serialVersionUID = -1689265019L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodMenu foodMenu = new QFoodMenu("foodMenu");

    public final NumberPath<Float> discount_price = createNumber("discount_price", Float.class);

    public final EnumPath<dev.capstone.domain.enumerated.YesOrNo> in_stock = createEnum("in_stock", dev.capstone.domain.enumerated.YesOrNo.class);

    public final QMarket market;

    public final StringPath menu_description = createString("menu_description");

    public final NumberPath<Integer> menu_id = createNumber("menu_id", Integer.class);

    public final StringPath menu_image = createString("menu_image");

    public final StringPath menu_name = createString("menu_name");

    public final EnumPath<dev.capstone.domain.enumerated.Level> menu_type = createEnum("menu_type", dev.capstone.domain.enumerated.Level.class);

    public final ListPath<dev.capstone.domain.jointable.MenuReview, QMenuReview> menuReviews = this.<dev.capstone.domain.jointable.MenuReview, QMenuReview>createList("menuReviews", dev.capstone.domain.jointable.MenuReview.class, QMenuReview.class, PathInits.DIRECT2);

    public final MapPath<String, Object, SimplePath<Object>> options = this.<String, Object, SimplePath<Object>>createMap("options", String.class, Object.class, SimplePath.class);

    public final NumberPath<Float> price = createNumber("price", Float.class);

    public QFoodMenu(String variable) {
        this(FoodMenu.class, forVariable(variable), INITS);
    }

    public QFoodMenu(Path<? extends FoodMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodMenu(PathMetadata metadata, PathInits inits) {
        this(FoodMenu.class, metadata, inits);
    }

    public QFoodMenu(Class<? extends FoodMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new QMarket(forProperty("market"), inits.get("market")) : null;
    }

}

