package Problem.Math.AI.domain.attempt.entity;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("pending"),
    SUCCESS("success"),
    FAIL("fail");

    private final String status;

    Status (String status){
        this.status = status;
    }

}
