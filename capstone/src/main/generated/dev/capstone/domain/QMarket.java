package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarket is a Querydsl query type for Market
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarket extends EntityPathBase<Market> {

    private static final long serialVersionUID = -654408732L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarket market = new QMarket("market");

    public final EnumPath<dev.capstone.domain.enumerated.YesOrNo> certified = createEnum("certified", dev.capstone.domain.enumerated.YesOrNo.class);

    public final ListPath<Guest, QGuest> guests = this.<Guest, QGuest>createList("guests", Guest.class, QGuest.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath location = createString("location");

    public final QMainUser mainUser;

    public final StringPath name = createString("name");

    public final ListPath<dev.capstone.domain.jointable.OrderList, dev.capstone.domain.jointable.QOrderList> orderLists = this.<dev.capstone.domain.jointable.OrderList, dev.capstone.domain.jointable.QOrderList>createList("orderLists", dev.capstone.domain.jointable.OrderList.class, dev.capstone.domain.jointable.QOrderList.class, PathInits.DIRECT2);

    public QMarket(String variable) {
        this(Market.class, forVariable(variable), INITS);
    }

    public QMarket(Path<? extends Market> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarket(PathMetadata metadata, PathInits inits) {
        this(Market.class, metadata, inits);
    }

    public QMarket(Class<? extends Market> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainUser = inits.isInitialized("mainUser") ? new QMainUser(forProperty("mainUser")) : null;
    }

}

