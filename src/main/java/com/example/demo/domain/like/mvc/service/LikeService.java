package com.example.demo.domain.like.mvc.service;

import com.example.demo.domain.like.exception.LikeException;
import com.example.demo.domain.like.kafka.event.LikeAddRequestEvent;
import com.example.demo.domain.like.kafka.publisher.LikeAddRequestPublisher;
import com.example.demo.domain.like.mvc.dto.LikeAddRequest;
import com.example.demo.domain.like.mvc.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeAddRequestPublisher likeAddRequestPublisher;

    public void likeAddRequest(LikeAddRequest addRequest){

        try{
            validDomainAddRequest(addRequest);
        }catch (RuntimeException e){
            throw new LikeException(e.getMessage(), e);
        }

        LikeAddRequestEvent event = LikeAddRequestEvent.builder()
                .createdAt(ZonedDateTime.now())
                .failureMessages(new ArrayList<>())
                .likeAddRequest(addRequest)
                .likeAddRequestEventDomainEventPublisher(likeAddRequestPublisher)
                .build();
        try{
            event.fire();
        } catch (RuntimeException e){
            log.error(e.getMessage(), e);
            throw new KafkaException("Like add reuqest "+e.getMessage(), e);
        }
    }

    public void validDomainAddRequest(LikeAddRequest addRequest) {
        boolean valid;
        switch (addRequest.getType()){
            case PROBLEM -> valid = likeRepository.exitsProblemById(addRequest.getRelationId(), addRequest.getReceiverId());
            case ATTEMPT -> valid = likeRepository.exitsAttemptById(addRequest.getRelationId() , addRequest.getReceiverId());
            case REVIEW -> valid = likeRepository.exitsReviewById(addRequest.getRelationId() , addRequest.getReceiverId());
            case QUESTION -> valid = likeRepository.exitsQuestionById(addRequest.getRelationId() , addRequest.getReceiverId());
            case ANSWER -> valid = likeRepository.exitsAnswerById(addRequest.getRelationId() , addRequest.getReceiverId());
            default -> throw new LikeException("존재하지 않는 도메인 타입입니다.");
        }
        if(!valid){
            throw new LikeException("type : "+addRequest.getType()+" "+" id : "+addRequest.getRelationId()+" 해당 데이터는 존재하지 않습니다.");
        }
    }
}
