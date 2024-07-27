package com.example.demo.domain.like.mvc.controller;

import com.example.demo.domain.like.mvc.dto.LikeAddRequest;
import com.example.demo.domain.like.mvc.service.LikeService;
import lombok.RequiredArgsConstructor;
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
    public void createLike(@RequestBody LikeAddRequest addRequest){

    }
}
