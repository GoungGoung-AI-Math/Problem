package math.ai.my.kafka.infra.listener;

public interface DomainEvent<T> {
    void fire();
}