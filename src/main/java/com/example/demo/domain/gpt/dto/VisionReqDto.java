package com.example.demo.domain.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisionReqDto {
    private List<ReqContent> contents;
    public VisionReqDto(ReqContent content){
        this.contents = List.of(content);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(ReqContent reqContent : contents){
            sb.append(reqContent).append("\n");
        }
        return sb.toString();
    }
}