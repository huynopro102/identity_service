package identity.TuanHuy.configuration;

import com.cloudinary.Cloudinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private Logger LOGGER = LoggerFactory.getLogger(CloudinaryConfig.class);

    @Value("${spring.cloudinary.cloud-name}")
    private String cloudName;

    @Value("${spring.cloudinary.api-key}")
    private String cloudApiKey;

    @Value("${spring.cloudinary.api-secret}")
    private String cloudApiSecret;

    @Bean
    public Cloudinary cloudinary(){
        LOGGER.info("this is a cloud-name "+cloudName);
        LOGGER.info("this is a cloud-api-key "+cloudApiKey);
        LOGGER.info("this is a cloud-secret-key "+cloudApiSecret);
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name",cloudName);
        config.put("cloud_api_key",cloudApiKey);
        config.put("cloud_api_secret",cloudApiSecret);
        return new Cloudinary(config);
    }

}
