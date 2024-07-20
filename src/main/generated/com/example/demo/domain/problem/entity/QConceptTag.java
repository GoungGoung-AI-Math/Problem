package com.example.demo.domain.problem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConceptTag is a Querydsl query type for ConceptTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConceptTag extends EntityPathBase<ConceptTag> {

    private static final long serialVersionUID = -155614114L;

    public static final QConceptTag conceptTag = new QConceptTag("conceptTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath tageName = createString("tageName");

    public QConceptTag(String variable) {
        super(ConceptTag.class, forVariable(variable));
    }

    public QConceptTag(Path<? extends ConceptTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConceptTag(PathMetadata metadata) {
        super(ConceptTag.class, metadata);
    }

}

