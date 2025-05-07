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
    public TopicExchange exchange(RabbitConstants constants)
    {
        return new TopicExchange(constants.getExchange());
    }
    //Queues
    @Bean
    public Queue saveQueue(RabbitConstants constants){
        return new Queue(constants.getQueue().getSave());
    }
    @Bean
    public Queue showQueue(RabbitConstants constants){
        return new Queue(constants.getQueue().getShow());
    }
    @Bean
    public Queue showAnswerQueue(RabbitConstants constants){
        return new Queue(constants.getQueue().getShowAnswer());
    }
    @Bean
    public Queue saveAnswerQueue(RabbitConstants constants){
        return new Queue(constants.getQueue().getSaveAnswer());
    }
    //Binding
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange, RabbitConstants constants){
        return BindingBuilder
                .bind(saveQueue)
                .to(exchange).with(constants.getRoutingKey().getSave());
    }
    @Bean
    public Binding showBinding(Queue showQueue, TopicExchange exchange,RabbitConstants constants){
        return BindingBuilder
                .bind(showQueue)
                .to(exchange).with(constants.getRoutingKey().getShow());
    }
    @Bean
    public Binding showAnswerBinding(Queue showAnswerQueue, TopicExchange exchange, RabbitConstants constants){
        return BindingBuilder
                .bind(showAnswerQueue)
                .to(exchange).with(constants.getRoutingKey().getShowAnswer());
    }
    @Bean
    public Binding saveAnswerBinding(Queue saveAnswerQueue, TopicExchange exchange, RabbitConstants constants){
        return BindingBuilder
                .bind(saveAnswerQueue)
                .to(exchange).with(constants.getRoutingKey().getSaveAnswer());
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
