package ptzt.f1hub_auth.infrastructure.adapter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public final static String LOGIN_QUEUE = "auth.login";
    public final static String REGISTER_QUEUE = "auth.register";
    public final static String DEACTIVATE_QUEUE = "auth.deactivate";
    public final static String RESTORE_PASSWORD_QUEUE = "auth.restore_password";

    @Bean
    public TopicExchange topicExchange(
            @Value("${app.rabbitmq.exchange}") String exchangeName) {
        return new TopicExchange(exchangeName, true, false);
    }

    @Bean
    public Queue credentialsQueue(
            @Value("${app.rabbitmq.queue}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding credentialsBinding(
            Queue credentialsQueue,
            TopicExchange topicExchange,
            @Value("${app.rabbitmq.routing-key}") String routingKey) {

        return BindingBuilder
                .bind(credentialsQueue)
                .to(topicExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
