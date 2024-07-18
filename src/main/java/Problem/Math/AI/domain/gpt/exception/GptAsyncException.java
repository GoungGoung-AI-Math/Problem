package Problem.Math.AI.domain.gpt.exception;

public class GptAsyncException extends RuntimeException {
    public GptAsyncException(String message, Throwable g) {
        super(message, g);
    }

    public GptAsyncException(String msg){
        super(msg);
    }
}
