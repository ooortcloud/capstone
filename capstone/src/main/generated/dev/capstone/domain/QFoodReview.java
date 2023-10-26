package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import dev.capstone.domain.jointable.QMenuReview;


/**
 * QFoodReview is a Querydsl query type for FoodReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodReview extends EntityPathBase<FoodReview> {

    private static final long serialVersionUID = 257330430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodReview foodReview = new QFoodReview("foodReview");

    public final StringPath body = createString("body");

    public final QMarket market;

    public final ListPath<dev.capstone.domain.jointable.MenuReview, QMenuReview> menuReviews = this.<dev.capstone.domain.jointable.MenuReview, QMenuReview>createList("menuReviews", dev.capstone.domain.jointable.MenuReview.class, QMenuReview.class, PathInits.DIRECT2);

    public final NumberPath<Integer> review_id = createNumber("review_id", Integer.class);

    public final NumberPath<Float> stars = createNumber("stars", Float.class);

    public QFoodReview(String variable) {
        this(FoodReview.class, forVariable(variable), INITS);
    }

    public QFoodReview(Path<? extends FoodReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodReview(PathMetadata metadata, PathInits inits) {
        this(FoodReview.class, metadata, inits);
    }

    public QFoodReview(Class<? extends FoodReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new QMarket(forProperty("market"), inits.get("market")) : null;
    }

}

