package Problem.Math.AI.common;

import lombok.Getter;

@Getter
public enum AnalysisType {
    ATTEMPT("attempt"),
    QUESTION("question");

    private final String type;

    AnalysisType (String type){
        this.type = type;
    }

}
