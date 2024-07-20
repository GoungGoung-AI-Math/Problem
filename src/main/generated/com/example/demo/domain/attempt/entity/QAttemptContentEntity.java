package com.example.demo.domain.attempt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttemptContentEntity is a Querydsl query type for AttemptContentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttemptContentEntity extends EntityPathBase<AttemptContentEntity> {

    private static final long serialVersionUID = 1735784553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttemptContentEntity attemptContentEntity = new QAttemptContentEntity("attemptContentEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final QProblemAttempt problemAttempt;

    public QAttemptContentEntity(String variable) {
        this(AttemptContentEntity.class, forVariable(variable), INITS);
    }

    public QAttemptContentEntity(Path<? extends AttemptContentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttemptContentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttemptContentEntity(PathMetadata metadata, PathInits inits) {
        this(AttemptContentEntity.class, metadata, inits);
    }

    public QAttemptContentEntity(Class<? extends AttemptContentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problemAttempt = inits.isInitialized("problemAttempt") ? new QProblemAttempt(forProperty("problemAttempt"), inits.get("problemAttempt")) : null;
    }

}

