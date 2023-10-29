package dev.capstone.domain.jointable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderList is a Querydsl query type for OrderList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderList extends EntityPathBase<OrderList> {

    private static final long serialVersionUID = -948044326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderList orderList = new QOrderList("orderList");

    public final dev.capstone.domain.QGuest guest;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final dev.capstone.domain.QMarket market;

    public final MapPath<String, Integer, NumberPath<Integer>> order_map = this.<String, Integer, NumberPath<Integer>>createMap("order_map", String.class, Integer.class, NumberPath.class);

    public QOrderList(String variable) {
        this(OrderList.class, forVariable(variable), INITS);
    }

    public QOrderList(Path<? extends OrderList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderList(PathMetadata metadata, PathInits inits) {
        this(OrderList.class, metadata, inits);
    }

    public QOrderList(Class<? extends OrderList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guest = inits.isInitialized("guest") ? new dev.capstone.domain.QGuest(forProperty("guest"), inits.get("guest")) : null;
        this.market = inits.isInitialized("market") ? new dev.capstone.domain.QMarket(forProperty("market"), inits.get("market")) : null;
    }

}

