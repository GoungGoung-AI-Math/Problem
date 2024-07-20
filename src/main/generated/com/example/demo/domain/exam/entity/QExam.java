package com.example.demo.domain.exam.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExam is a Querydsl query type for Exam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExam extends EntityPathBase<Exam> {

    private static final long serialVersionUID = 1989872459L;

    public static final QExam exam = new QExam("exam");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final EnumPath<Difficulty> difficulty = createEnum("difficulty", Difficulty.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<com.example.demo.domain.problem.entity.Problem, com.example.demo.domain.problem.entity.QProblem> problems = this.<com.example.demo.domain.problem.entity.Problem, com.example.demo.domain.problem.entity.QProblem>createSet("problems", com.example.demo.domain.problem.entity.Problem.class, com.example.demo.domain.problem.entity.QProblem.class, PathInits.DIRECT2);

    public final EnumPath<RevisionState> revisionState = createEnum("revisionState", RevisionState.class);

    public final NumberPath<Long> totalSolved = createNumber("totalSolved", Long.class);

    public final EnumPath<Type> type = createEnum("type", Type.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QExam(String variable) {
        super(Exam.class, forVariable(variable));
    }

    public QExam(Path<? extends Exam> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExam(PathMetadata metadata) {
        super(Exam.class, metadata);
    }

}

