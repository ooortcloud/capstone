package dev.capstone.domain.jointable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuReview is a Querydsl query type for MenuReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuReview extends EntityPathBase<MenuReview> {

    private static final long serialVersionUID = -533748087L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuReview menuReview = new QMenuReview("menuReview");

    public final dev.capstone.domain.QFoodMenu foodMenu;

    public final dev.capstone.domain.QFoodReview foodReview;

    public final NumberPath<Integer> menureview_id = createNumber("menureview_id", Integer.class);

    public QMenuReview(String variable) {
        this(MenuReview.class, forVariable(variable), INITS);
    }

    public QMenuReview(Path<? extends MenuReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuReview(PathMetadata metadata, PathInits inits) {
        this(MenuReview.class, metadata, inits);
    }

    public QMenuReview(Class<? extends MenuReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodMenu = inits.isInitialized("foodMenu") ? new dev.capstone.domain.QFoodMenu(forProperty("foodMenu"), inits.get("foodMenu")) : null;
        this.foodReview = inits.isInitialized("foodReview") ? new dev.capstone.domain.QFoodReview(forProperty("foodReview"), inits.get("foodReview")) : null;
    }

}

