package com.example.demo.domain.attempt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblemAttempt is a Querydsl query type for ProblemAttempt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblemAttempt extends EntityPathBase<ProblemAttempt> {

    private static final long serialVersionUID = -1745581272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblemAttempt problemAttempt = new QProblemAttempt("problemAttempt");

    public final NumberPath<Integer> answer = createNumber("answer", Integer.class);

    public final SetPath<AttemptContentEntity, QAttemptContentEntity> attemptContents = this.<AttemptContentEntity, QAttemptContentEntity>createSet("attemptContents", AttemptContentEntity.class, QAttemptContentEntity.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.demo.domain.problem.entity.QProblem problem;

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QProblemAttempt(String variable) {
        this(ProblemAttempt.class, forVariable(variable), INITS);
    }

    public QProblemAttempt(Path<? extends ProblemAttempt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblemAttempt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblemAttempt(PathMetadata metadata, PathInits inits) {
        this(ProblemAttempt.class, metadata, inits);
    }

    public QProblemAttempt(Class<? extends ProblemAttempt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problem = inits.isInitialized("problem") ? new com.example.demo.domain.problem.entity.QProblem(forProperty("problem"), inits.get("problem")) : null;
    }

}

