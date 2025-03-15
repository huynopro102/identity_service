package identity.TuanHuy.kafka;
import identity.TuanHuy.dto.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonkafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonkafkaProducer.class);

    private KafkaTemplate<String , UserResponse> kafkaTemplate;

    public JsonkafkaProducer(KafkaTemplate<String , UserResponse> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserResponse userResponse){

        LOGGER.info(String.format("Json Message sent %s ",userResponse));

        Message<UserResponse> message = MessageBuilder
                .withPayload(userResponse)
                .setHeader(KafkaHeaders.TOPIC,"message_java")
                .build()
                ;
        kafkaTemplate.send(message);
    }
}
