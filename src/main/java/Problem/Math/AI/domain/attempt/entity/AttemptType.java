package Problem.Math.AI.domain.attempt.entity;

import lombok.Getter;

@Getter
public enum AttemptType {
    TEXT("text"),
    IMAGE_URL("url");

    private final String type;
    AttemptType(String type){
        this.type = type;
    }
}
