package com.yasuodemo.backend.controller.dogListener;

import com.yasuodemo.backend.service.DogService;
import constant.RabbitConstants;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class SaveListener implements MessageListener {
    private DogService dogService;
    private RabbitTemplate template;
    private RabbitConstants constants;
    @Override
    public void onMessage(Message message) {
        DogDto dog = (DogDto) template.getMessageConverter().fromMessage(message);
        List<DogDto> saveDogs = dogService.saveDog(dog);
        template.convertAndSend(constants.exchange,constants.saveAnswerRoutingKey,saveDogs);
    }
}
