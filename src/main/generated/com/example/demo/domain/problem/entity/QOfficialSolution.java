package com.example.demo.domain.problem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOfficialSolution is a Querydsl query type for OfficialSolution
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOfficialSolution extends EntityPathBase<OfficialSolution> {

    private static final long serialVersionUID = -1199933232L;

    public static final QOfficialSolution officialSolution = new QOfficialSolution("officialSolution");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<SolutionContentEntity, QSolutionContentEntity> solutionContents = this.<SolutionContentEntity, QSolutionContentEntity>createSet("solutionContents", SolutionContentEntity.class, QSolutionContentEntity.class, PathInits.DIRECT2);

    public final StringPath source = createString("source");

    public final StringPath textSolution = createString("textSolution");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QOfficialSolution(String variable) {
        super(OfficialSolution.class, forVariable(variable));
    }

    public QOfficialSolution(Path<? extends OfficialSolution> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOfficialSolution(PathMetadata metadata) {
        super(OfficialSolution.class, metadata);
    }

}

