package dev.capstone.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMainUser is a Querydsl query type for MainUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMainUser extends EntityPathBase<MainUser> {

    private static final long serialVersionUID = -2076932404L;

    public static final QMainUser mainUser = new QMainUser("mainUser");

    public final StringPath id = createString("id");

    public final ListPath<Market, QMarket> markets = this.<Market, QMarket>createList("markets", Market.class, QMarket.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public final NumberPath<Integer> user_id = createNumber("user_id", Integer.class);

    public final StringPath user_residence = createString("user_residence");

    public QMainUser(String variable) {
        super(MainUser.class, forVariable(variable));
    }

    public QMainUser(Path<? extends MainUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainUser(PathMetadata metadata) {
        super(MainUser.class, metadata);
    }

}

