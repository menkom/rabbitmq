package info.mastera.listener;

import info.mastera.config.Settings;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

@Component
@RabbitListeners({
// For listening for existing queue
//        @RabbitListener(queues = RabbitConfiguration.EMAIL_QUEUE_NAME),
// For creating queue bind to exchange AND listening for this queue
        @RabbitListener(
                bindings = @QueueBinding(
                        value = @Queue(value = Settings.EMAIL_QUEUE_NAME, durable = "false"),
                        exchange = @Exchange(name = Settings.MESSAGE_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
                )
        )
})
public class EmailQueueListener {

    @RabbitHandler
    public void receivedMessageInQueue(String message) {
        System.out.println("Listener for emails : " + message);
    }
}
