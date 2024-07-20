package com.example.demo.domain.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionContentEntity is a Querydsl query type for QuestionContentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionContentEntity extends EntityPathBase<QuestionContentEntity> {

    private static final long serialVersionUID = 1023052414L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionContentEntity questionContentEntity = new QQuestionContentEntity("questionContentEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final QQuestion question;

    public QQuestionContentEntity(String variable) {
        this(QuestionContentEntity.class, forVariable(variable), INITS);
    }

    public QQuestionContentEntity(Path<? extends QuestionContentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionContentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionContentEntity(PathMetadata metadata, PathInits inits) {
        this(QuestionContentEntity.class, metadata, inits);
    }

    public QQuestionContentEntity(Class<? extends QuestionContentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

