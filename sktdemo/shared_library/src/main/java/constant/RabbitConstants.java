package constant;

import org.springframework.beans.factory.annotation.Value;

public class RabbitConstants {
    @Value("${rabbit.exchange}")
    public String exchange;

    @Value("${rabbit.queue.save}")
    public String saveQueue;
    @Value("${rabbit.queue.show}")
    public String showQueue;
    @Value("${rabbit.queue.show_answer}")
    public String showAnswerQueue;

    @Value("${rabbit.routing_key.save}")
    public String saveRoutingKey;
    @Value("${rabbit.routing_key.show}")
    public String showRoutingKey;
    @Value("${rabbit.routing_key.show_answer}")
    public String showAnswerRoutingKey;
}
