package info.mastera.listener;

import info.mastera.config.Settings;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

@Component
@RabbitListeners({
// For listening for existing queue
//        @RabbitListener(queues = RabbitConfiguration.SIMPLE_QUEUE_NAME),
// For creating queue AND listening for this queue
        @RabbitListener(queuesToDeclare = @Queue(value = Settings.SIMPLE_QUEUE_NAME, durable = "false"))
})
public class SimpleQueueListener {

    @RabbitHandler
    public void receivedMessageInQueue(String message) {
        System.out.println("received from " + Settings.SIMPLE_QUEUE_NAME + " : " + message);
    }
}
