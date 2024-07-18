package Problem.Math.AI.domain.problem.exception;

public class ProblemException extends RuntimeException{
    public ProblemException(String msg, Throwable cause){
        super(msg, cause);
    }
    public ProblemException(String msg){
        super(msg);
    }
}
