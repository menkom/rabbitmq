package info.mastera.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String SIMPLE_QUEUE_NAME = "simple_queue";

    @Bean
    Queue queue() {
        return new Queue(SIMPLE_QUEUE_NAME, true);
    }
}
