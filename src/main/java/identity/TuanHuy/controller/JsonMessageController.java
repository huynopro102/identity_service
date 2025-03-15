package identity.TuanHuy.controller;


import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.kafka.JsonkafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
    private JsonkafkaProducer jsonkafkaProducer;

    public JsonMessageController(JsonkafkaProducer jsonkafkaProducer){
        this.jsonkafkaProducer = jsonkafkaProducer;
    }


    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody UserResponse userResponse){
            jsonkafkaProducer.sendMessage(userResponse);
            return ResponseEntity.ok("json  message sent topic kafka");
    }
}
