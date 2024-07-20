package com.example.demo.domain.problem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSolutionContentEntity is a Querydsl query type for SolutionContentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSolutionContentEntity extends EntityPathBase<SolutionContentEntity> {

    private static final long serialVersionUID = -2102608649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSolutionContentEntity solutionContentEntity = new QSolutionContentEntity("solutionContentEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final QOfficialSolution officialSolution;

    public QSolutionContentEntity(String variable) {
        this(SolutionContentEntity.class, forVariable(variable), INITS);
    }

    public QSolutionContentEntity(Path<? extends SolutionContentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSolutionContentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSolutionContentEntity(PathMetadata metadata, PathInits inits) {
        this(SolutionContentEntity.class, metadata, inits);
    }

    public QSolutionContentEntity(Class<? extends SolutionContentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.officialSolution = inits.isInitialized("officialSolution") ? new QOfficialSolution(forProperty("officialSolution")) : null;
    }

}

