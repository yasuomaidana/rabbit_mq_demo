package com.yasuodemo.backend.controller.dogListener;

import com.yasuodemo.backend.service.DogService;
import dto.DogDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class SaveListener implements MessageListener {
    private DogService dogService;
    private RabbitTemplate template;
    @Override
    public void onMessage(Message message) {
        DogDto dog = (DogDto) template.getMessageConverter().fromMessage(message);
        dogService.saveDog(dog);
    }
}
