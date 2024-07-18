package Problem.Math.AI.domain.attempt.exception;

public class AttemptException extends RuntimeException{
    public AttemptException(String msg, Throwable cause){
        super(msg, cause);
    }
    public AttemptException(String msg){
        super(msg);
    }
}
