package identity.TuanHuy.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;


@Component
public class DatabaseConnection {

    private static Environment env;

    // Static giúp đảm bảo pool này tồn tại xuyên suốt vòng đời ứng dụng, tránh việc tạo nhiều pool không cần thiết.
    private static HikariDataSource dataSource;

    public DatabaseConnection(Environment env){
        this.env = env;
        init(); // gọi hàm init sau khi spring boot khỏi tạo bean DatabaseConnection
    }

    public void init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password"));
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setConnectionTimeout(30000);
        dataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DatabaseConnection is not initialized.");
        }
        return dataSource.getConnection();
    }

}
