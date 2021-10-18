package config;

import constant.RabbitConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String SHOW_QUEUE="show_queue";
    public static final String SHOW_ANSWER_QUEUE="show_answer_queue";
    public static final String SAVE_ROUTING_KEY = "save_routingKey";
    public static final String SHOW_ROUTING_KEY = "show_routingKey";
    public static final String SHOW_ANSWER_ROUTING_KEY = "show_answer_routingKey";

    @Bean public RabbitConstants rabbitConstants(){
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
    public Queue showQueue(){return new Queue(SHOW_QUEUE);}
    @Bean
    public Queue showAnswerQueue(){return new Queue(SHOW_ANSWER_QUEUE);}
    //Binding
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(saveQueue)
                .to(exchange).with(SAVE_ROUTING_KEY);
    }
    @Bean
    public Binding showBinding(Queue showQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(showQueue)
                .to(exchange).with(SHOW_ROUTING_KEY);
    }
    @Bean
    public Binding showAnswerBinding(Queue showAnswerQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(showAnswerQueue)
                .to(exchange).with(SHOW_ANSWER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
