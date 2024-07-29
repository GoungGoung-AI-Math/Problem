package temp.infra.kafka.publisher.kafka;

public interface DomainEvent<T> {
    void fire();
}