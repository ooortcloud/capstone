package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuest is a Querydsl query type for Guest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuest extends EntityPathBase<Guest> {

    private static final long serialVersionUID = -1272993488L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuest guest = new QGuest("guest");

    public final EnumPath<dev.capstone.domain.enumerated.YesOrNo> approved = createEnum("approved", dev.capstone.domain.enumerated.YesOrNo.class);

    public final StringPath details = createString("details");

    public final NumberPath<Integer> gnumber = createNumber("gnumber", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMarket market;

    public final NumberPath<Integer> number_of_people = createNumber("number_of_people", Integer.class);

    public final dev.capstone.domain.jointable.QOrderList orderList;

    public final NumberPath<Integer> tableNum = createNumber("tableNum", Integer.class);

    public final StringPath token = createString("token");

    public final EnumPath<dev.capstone.domain.enumerated.YesOrNo> waiting = createEnum("waiting", dev.capstone.domain.enumerated.YesOrNo.class);

    public QGuest(String variable) {
        this(Guest.class, forVariable(variable), INITS);
    }

    public QGuest(Path<? extends Guest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuest(PathMetadata metadata, PathInits inits) {
        this(Guest.class, metadata, inits);
    }

    public QGuest(Class<? extends Guest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new QMarket(forProperty("market"), inits.get("market")) : null;
        this.orderList = inits.isInitialized("orderList") ? new dev.capstone.domain.jointable.QOrderList(forProperty("orderList"), inits.get("orderList")) : null;
    }

}

