package identity.TuanHuy.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;

@Configuration
public class OpenAPIConfig {

    private SecurityScheme createAPIKeysScheme(){
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }


    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()

                .servers(Arrays.asList(
                        new Server().url("https://huynguyen-nginx.io.vn:8887").description("Production server") ,
                        new Server().url("https://staging.api.com").description("Staging server") ,
                        new Server().url("https://api.com").description("Production server") ,
                        new Server().url("http://localhost:8080").description("localhost server frontend")

                ))

                .components( new Components()
                    .addSecuritySchemes("bearer-key",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                )

                .info(new Info()
                        .title("TuanHuy API Documentation")
                        .version("1.0.0")
                        .description("API Documentation for TuanHuy project\n\n" +
                                "Để sử dụng API, bạn cần:\n" +
                                "1. Click vào nút 'Authorize' phía trên\n" +
                                "2. Nhập token theo định dạng: Bearer your-token-here\n" +
                                "3. Click 'Authorize' để áp dụng token cho tất cả API")
                );
    }
}
