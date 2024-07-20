package com.example.demo.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContentEntity is a Querydsl query type for ContentEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QContentEntity extends EntityPathBase<ContentEntity> {

    private static final long serialVersionUID = -365639878L;

    public static final QContentEntity contentEntity = new QContentEntity("contentEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public QContentEntity(String variable) {
        super(ContentEntity.class, forVariable(variable));
    }

    public QContentEntity(Path<? extends ContentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContentEntity(PathMetadata metadata) {
        super(ContentEntity.class, metadata);
    }

}

