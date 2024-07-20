package com.example.demo.domain.problem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblem is a Querydsl query type for Problem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblem extends EntityPathBase<Problem> {

    private static final long serialVersionUID = 1160041939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblem problem = new QProblem("problem");

    public final NumberPath<Integer> answer = createNumber("answer", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Double> difficulty = createNumber("difficulty", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final QOfficialSolution officialSolution;

    public final SetPath<ProblemConceptTag, QProblemConceptTag> problemConceptTags = this.<ProblemConceptTag, QProblemConceptTag>createSet("problemConceptTags", ProblemConceptTag.class, QProblemConceptTag.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QProblem(String variable) {
        this(Problem.class, forVariable(variable), INITS);
    }

    public QProblem(Path<? extends Problem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblem(PathMetadata metadata, PathInits inits) {
        this(Problem.class, metadata, inits);
    }

    public QProblem(Class<? extends Problem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.officialSolution = inits.isInitialized("officialSolution") ? new QOfficialSolution(forProperty("officialSolution")) : null;
    }

}

