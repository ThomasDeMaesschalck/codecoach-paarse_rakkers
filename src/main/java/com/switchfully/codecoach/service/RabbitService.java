package com.switchfully.codecoach.service;

import com.switchfully.codecoach.domain.Session;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    public final static String EXCHANGE_NAME = "codecoach";

    @Autowired
    private RabbitTemplate template;

    public void sendMessageToTopic(String message, String topic) {
        MessageProperties messageProperties = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .build();
        template.setExchange(EXCHANGE_NAME);
        template.setRoutingKey(topic);
        template.send(new Message(message.getBytes(), messageProperties));
    }

    public String createRequestSessionMessage(Session session) {
        return String.format("Session requested by %s %s on %s", session.getCoachee().getFirstName(), 
                session.getCoachee().getLastName(), session.getMoment());
    }
}
