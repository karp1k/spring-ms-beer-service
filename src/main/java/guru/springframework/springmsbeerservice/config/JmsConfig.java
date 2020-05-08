package guru.springframework.springmsbeerservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * https://github.com/vromero/activemq-artemis-docker
 * @author kas
 */
@Configuration
public class JmsConfig {

    public static final String BREWING_REQUEST_QUEUE = "brewing-request";


    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        // will convert POJO to JSON string
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setObjectMapper(objectMapper);
        // for spring processing. Spring sets value of property equal to specific java class. Check @Payload in HelloListener
        // for @Payload HelloWorldMessage would be: _type = guru.springframework.sfgjms.model.HelloWorldMessage
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
