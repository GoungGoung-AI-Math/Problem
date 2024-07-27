package com.example.demo.domain.like.mvc.controller;

import com.example.demo.domain.like.mvc.dto.likeAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class DomainLikeController {



    @PostMapping
    public void createLike(@RequestBody likeAddRequest addRequest){

    }
}
