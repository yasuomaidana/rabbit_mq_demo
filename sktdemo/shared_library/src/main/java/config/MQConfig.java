package config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import constant.RabbitConstants;

@Configuration
public class MQConfig {

    @Bean
    public RabbitConstants rabbitConstants(){
        return new RabbitConstants();
    }
    @Bean
    public TopicExchange exchange(RabbitConstants constants)
    {
        return new TopicExchange(constants.exchange);
    }
    //Queues
    @Bean
    public Queue saveQueue(RabbitConstants constants){
        return new Queue(constants.saveQueue);
    }
    @Bean
    public Queue showQueue(){
        return new Queue(rabbitConstants().showQueue);
    }
    @Bean
    public Queue showAnswerQueue(){
        return new Queue(rabbitConstants().showAnswerQueue);
    }
    @Bean
    public Queue saveAnswerQueue(){
        return new Queue(rabbitConstants().saveAnswerQueue);
    }
    //Binding
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(saveQueue)
                .to(exchange).with(rabbitConstants().saveRoutingKey);
    }
    @Bean
    public Binding showBinding(Queue showQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(showQueue)
                .to(exchange).with(rabbitConstants().showRoutingKey);
    }
    @Bean
    public Binding showAnswerBinding(Queue showAnswerQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(showAnswerQueue)
                .to(exchange).with(rabbitConstants().showAnswerRoutingKey);
    }
    @Bean
    public Binding saveAnswerBinding(Queue saveAnswerQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(saveAnswerQueue)
                .to(exchange).with(rabbitConstants().saveAnswerRoutingKey);
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
