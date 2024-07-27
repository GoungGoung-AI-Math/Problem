package com.example.demo.domain.like.mvc.controller;

import com.example.demo.domain.like.mvc.dto.LikeAddRequest;
import com.example.demo.domain.like.mvc.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class DomainLikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> createLike(@RequestBody LikeAddRequest addRequest){
        likeService.likeAddRequest(addRequest);
        return ResponseEntity.ok("좋아요를 추가했습니다.");
    }
}
