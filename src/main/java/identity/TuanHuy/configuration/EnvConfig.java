package identity.TuanHuy.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class EnvConfig {

    @Bean
    public Dotenv dotenv(){

        Dotenv dotenv = Dotenv.configure()
                .load();  // Không cần .filename(".env") vì mặc định là tìm .env trong thư mục gốc

        // Nạp giá trị từ .env vào System properties
        dotenv.entries().forEach(entry ->{
            System.setProperty(entry.getKey(),entry.getValue());
        });
        return dotenv;
    }
}
