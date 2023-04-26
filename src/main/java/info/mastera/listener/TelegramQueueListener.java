package info.mastera.listener;

import info.mastera.config.Settings;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = Settings.TELEGRAM_QUEUE_NAME, durable = "false"),
                exchange = @Exchange(name = Settings.MESSAGE_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
        )
)
public class TelegramQueueListener {

    @RabbitHandler
    public void receivedMessageInQueue(String message) {
        System.out.println("Listener for telegram : " + message);
    }
}
