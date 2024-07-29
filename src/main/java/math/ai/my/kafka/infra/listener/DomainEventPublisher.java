package math.ai.my.kafka.infra.listener;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}