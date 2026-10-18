package ptzt.f1hub_auth.infrastructure.adapter.out.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ptzt.f1hub_auth.application.ports.out.AuthEventPublisher;

@Service
@RequiredArgsConstructor
public class AuthEventPublisherImpl implements AuthEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Override
    public void sendMessage(String key, Object object){
        rabbitTemplate.convertAndSend(
                exchange,
                key,
                object
        );
    }
}
