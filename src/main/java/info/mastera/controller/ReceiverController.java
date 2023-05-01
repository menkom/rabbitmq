package info.mastera.controller;

import info.mastera.config.Settings;
import info.mastera.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ReceiverController {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final RabbitTemplate template;

    @Autowired
    public ReceiverController(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * Endpoint to process message with only one consumer (one queue only)
     */
    @RequestMapping("/emit")
    @ResponseBody
    String simpleQueue() {
        System.out.println("Emit to " + Settings.SIMPLE_QUEUE_NAME);
        template.convertAndSend(Settings.SIMPLE_QUEUE_NAME,
                "Message to queue: " + counter.getAndIncrement() + " at " + LocalDateTime.now());
        return "Emit to queue " + counter.get();
    }

    /**
     * Endpoint to process the same message with several types of consumers (one exchange and several queues)
     */
    @RequestMapping("/message")
    @ResponseBody
    String message() {
        System.out.println("Received message.");
        template.convertAndSend(
                Settings.MESSAGE_EXCHANGE_NAME,
                Settings.TELEGRAM_QUEUE_NAME,
                "Message to exchange: " + counter.getAndIncrement() + " at " + LocalDateTime.now()
        );
        return "Received message " + counter.get();
    }

    /**
     * Endpoint to process not just simple String but complex objects
     */
    @RequestMapping("/object")
    @ResponseBody
    String object() {
        System.out.println("Received request for object transfer.");
        template.convertAndSend(
                Settings.OBJECT_TRANSFERRING_QUEUE_NAME,
                new Message()
                        .setMessage("Message with object")
                        .setCount(counter.getAndIncrement())
        );
        return "Received message " + counter.get();
    }
}
