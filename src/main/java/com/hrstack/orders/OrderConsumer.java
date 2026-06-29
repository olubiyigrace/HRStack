package com.hrstack.orders;

import com.hrstack.config.RabbitConfig;
import com.hrstack.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeMessage(@Payload ProducerMessage request) {
        log.info("Received: {}", request);

        if (request.getOtp() != null && "verifyAccount".equals(request.getPurpose())) {
            emailService.sendOtpEmail(request.getEmail(), request.getOtp());
            return;
        }

        emailService.sendSimpleEmail(
                request.getEmail(),
                "HRStack Notification",
                "Your request has been received and is being processed."
        );
    }
}
