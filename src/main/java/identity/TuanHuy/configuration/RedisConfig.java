package identity.TuanHuy.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.annotation.PostConstruct;
import java.time.Duration;

@Configuration
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.redis.port:6379}")
    private int redisPort;

    @Value("${spring.redis.username:}")
    private String redisUsername;

    @Value("${spring.redis.password:}")
    private String redisPassword;

    @Value("${spring.redis.ssl:false}")
    private boolean redisSsl;

    @Value("${spring.redis.timeout:5000}")
    private int redisTimeout;

    @PostConstruct
    public void logRedisConfiguration() {
        logger.info("🔍 Redis Configuration:");
        logger.info("📌 Host: {}", redisHost);
        logger.info("📌 Port: {}", redisPort);
        logger.info("📌 Username: {}", redisUsername.isEmpty() ? "<không có>" : "<được thiết lập>");
        logger.info("📌 Password: {}", redisPassword.isEmpty() ? "<không có>" : "<được thiết lập>");
        logger.info("📌 SSL: {}", redisSsl);
        logger.info("📌 Timeout: {} ms", redisTimeout);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);

        if (redisUsername != null && !redisUsername.isEmpty()) {
            redisConfig.setUsername(redisUsername);
        }

        if (redisPassword != null && !redisPassword.isEmpty()) {
            redisConfig.setPassword(redisPassword);
        }

        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder =
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(redisTimeout));

        if (redisSsl) {
            builder.useSsl();
        }

        return new LettuceConnectionFactory(redisConfig, builder.build());
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public Boolean testRedisConnection(RedisConnectionFactory redisConnectionFactory) {
        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            String ping = connection.ping();
            if ("PONG".equals(ping)) {
                logger.info("✅ Kết nối Redis thành công!");
                return true;
            } else {
                logger.warn("⚠️ Redis không phản hồi đúng (trả về: {})", ping);
                return false;
            }
        } catch (Exception e) {
            logger.error("❌ Lỗi khi kết nối Redis: {}", e.getMessage());
            logger.info("🔍 Đã thử kết nối tới Redis tại {}:{} với SSL={}", redisHost, redisPort, redisSsl);
            return false;
        }
    }
}