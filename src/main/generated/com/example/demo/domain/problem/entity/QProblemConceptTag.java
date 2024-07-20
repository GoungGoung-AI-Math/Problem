package com.example.demo.domain.problem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblemConceptTag is a Querydsl query type for ProblemConceptTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblemConceptTag extends EntityPathBase<ProblemConceptTag> {

    private static final long serialVersionUID = 751213125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblemConceptTag problemConceptTag = new QProblemConceptTag("problemConceptTag");

    public final QConceptTag conceptTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProblem problem;

    public QProblemConceptTag(String variable) {
        this(ProblemConceptTag.class, forVariable(variable), INITS);
    }

    public QProblemConceptTag(Path<? extends ProblemConceptTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblemConceptTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblemConceptTag(PathMetadata metadata, PathInits inits) {
        this(ProblemConceptTag.class, metadata, inits);
    }

    public QProblemConceptTag(Class<? extends ProblemConceptTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conceptTag = inits.isInitialized("conceptTag") ? new QConceptTag(forProperty("conceptTag")) : null;
        this.problem = inits.isInitialized("problem") ? new QProblem(forProperty("problem"), inits.get("problem")) : null;
    }

}

