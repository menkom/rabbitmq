package info.mastera.controller;

import info.mastera.config.RabbitConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ReceiverController {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final AmqpTemplate template;

    @Autowired
    public ReceiverController(AmqpTemplate template) {
        this.template = template;
    }

    @RequestMapping("/emit")
    @ResponseBody
    String queue1() {
        System.out.println("Emit to " + RabbitConfiguration.SIMPLE_QUEUE_NAME);
        template.convertAndSend(RabbitConfiguration.SIMPLE_QUEUE_NAME,
                "Message to queue: " + counter.getAndIncrement() + " at " + LocalDateTime.now());
        return "Emit to queue " + counter.get();
    }
}
