package com.hrstack.orders;

import com.hrstack.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeMessage(@Payload ProducerMessage request) {
        System.out.println("Received: " + request);
    }
}
