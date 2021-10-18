package com.yasuodemo.backend.controller.dogListener;

import com.yasuodemo.backend.config.MQConfig;
import com.yasuodemo.backend.service.DogService;
import constant.RabbitConstants;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ShowListener implements MessageListener {
    private DogService dogService;
    private RabbitTemplate template;
    private RabbitConstants constants;
    @Override
    public void onMessage(Message message) {
        template.convertAndSend(constants.exchange, MQConfig.SHOW_ANSWER_ROUTING_KEY,dogService.getDogs());
    }
}
