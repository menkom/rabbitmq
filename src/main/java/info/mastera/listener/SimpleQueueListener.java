package info.mastera.listener;

import info.mastera.config.RabbitConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfiguration.SIMPLE_QUEUE_NAME)
public class SimpleQueueListener {

    @RabbitHandler
    public void receivedMessageInQueue(String message) {
        System.out.println("received from " + RabbitConfiguration.SIMPLE_QUEUE_NAME + " : " + message);
    }
}
