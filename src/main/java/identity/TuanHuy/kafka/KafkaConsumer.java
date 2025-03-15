package identity.TuanHuy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "message_java" , groupId ="message_java" )
    public void consume(String message){
        LOGGER.info(String.format("Message Received -> %s",message));
    }
}
