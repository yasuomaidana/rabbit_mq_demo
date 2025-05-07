package constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbit")
@Data
public class RabbitConstants {
    private String exchange;
    private RabbitQueuesConfigurationProperties queue;
    private RabbitRoutingConfiguration routingKey;

}
