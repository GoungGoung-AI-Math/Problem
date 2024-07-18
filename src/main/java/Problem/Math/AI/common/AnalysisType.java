package Problem.Math.AI.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AnalysisType {
    ATTEMPT("attempt"),
    QUESTION("question");
    private final String type;
    AnalysisType (String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
