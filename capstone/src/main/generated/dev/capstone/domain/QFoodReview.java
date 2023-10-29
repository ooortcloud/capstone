package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodReview is a Querydsl query type for FoodReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodReview extends EntityPathBase<FoodReview> {

    private static final long serialVersionUID = 257330430L;

    public static final QFoodReview foodReview = new QFoodReview("foodReview");

    public final StringPath body = createString("body");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<dev.capstone.domain.jointable.MenuReview, dev.capstone.domain.jointable.QMenuReview> menuReviews = this.<dev.capstone.domain.jointable.MenuReview, dev.capstone.domain.jointable.QMenuReview>createList("menuReviews", dev.capstone.domain.jointable.MenuReview.class, dev.capstone.domain.jointable.QMenuReview.class, PathInits.DIRECT2);

    public final NumberPath<Float> stars = createNumber("stars", Float.class);

    public QFoodReview(String variable) {
        super(FoodReview.class, forVariable(variable));
    }

    public QFoodReview(Path<? extends FoodReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodReview(PathMetadata metadata) {
        super(FoodReview.class, metadata);
    }

}

