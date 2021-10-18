package constant;

import org.springframework.beans.factory.annotation.Value;

public class RabbitConstants {
    @Value("${rabbit.exchange}")
    public String exchange;
    @Value("${rabbit.queue.save}")
    public String saveQueue;
}
