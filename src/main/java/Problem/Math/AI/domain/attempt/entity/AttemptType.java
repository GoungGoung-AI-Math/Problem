package Problem.Math.AI.domain.attempt.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AttemptType {
    TEXT("text"),
    IMAGE_URL("url");

    private final String type;
    AttemptType(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
