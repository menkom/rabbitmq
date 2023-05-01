package info.mastera.listener;

import info.mastera.config.Settings;
import info.mastera.model.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

@Component
@RabbitListeners({
        @RabbitListener(
                queuesToDeclare = @Queue(value = Settings.OBJECT_TRANSFERRING_QUEUE_NAME, durable = "false")
                ,
                messageConverter = "jsonMessageConverter"
        )
})
public class ObjectTransferringQueueListener {

    @RabbitHandler
    public void receivedMessageInQueue(Message message) {
        System.out.println(
                "received from " + Settings.OBJECT_TRANSFERRING_QUEUE_NAME + " : " +
                        message.getMessage() + " | " +
                        message.getCount()
        );
    }
}
