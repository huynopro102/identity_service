    package identity.TuanHuy.configuration;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
    import org.springframework.security.oauth2.jwt.JwtDecoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import javax.crypto.spec.SecretKeySpec;
    import java.util.Arrays;


    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {


        private CustomAuthenticationEntryPoint authEntryPoint;

        public SecurityConfig(CustomAuthenticationEntryPoint authEntryPoint){
            this.authEntryPoint = authEntryPoint;
        }

        @Value("${spring.jwt.signerKey}")
        private String signerKey;


        private final String[] SWAGGER_WHITELIST={
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**"
        };


        private final String[] PUBLIC_ENDPOINTS_API_AUTHENTICATION = {
                "/api/authentication/login",
                "/api/mienphi",
                "/api/cloudinary/image/upload",
                "/api/genres" ,
        };

        private final String[] PUBLIC_ENDPOINTS_API_USER = {
                "/api/users" ,
                "/api/users/{userId}/role" ,
                "/api/users/{userId}"
        };

        private final String[] PUBLIC_ENDPOINTS_API_ROLE = {
                "/api/roles",
                "/api/roles/{roleName}",
                "/api/roles/*",
                "/api/roles/{roleName}/details"
        };

        private final String[] PUBLIC_ENDPOINTS_API_PERMISSION = {
                "/api/permissions/*",
                "/api/permissions"

        };

        private final String[] PUBLIC_ENDPOINTS_UI = {"/ui/home"};

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    // Cho phép các API không cần xác thực
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers(SWAGGER_WHITELIST).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_UI).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_USER).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_AUTHENTICATION).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_ROLE).permitAll()

                            // Đặc biệt: Cho phép đăng ký user mà không cần auth
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

                            // Chỉ cho phép GET user nếu đã xác thực
                            .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()

                            // Các API khác yêu cầu xác thực
                            .anyRequest().authenticated()
                    )

                    // Cấu hình OAuth2 Resource Server (JWT)
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtdecoder()))
                    )

                    // Cấu hình lỗi xác thực
                    .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .and()

                    // Cấu hình CORS
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                    // Vô hiệu hóa CSRF vì API không cần
                    .csrf().disable();

            return httpSecurity.build();
        }



        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            //Tạo một đối tượng CorsConfiguration
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(Arrays.asList("*"));
            //configuration.setAllowedOrigins(Arrays.asList("https://example.com", "https://myapp.com"));

            //Thiết lập danh sách các HTTP methods được phép
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

            //Thiết lập danh sách các header được phép trong request
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

            //Cho phép gửi credentials (cookies, token, session ID)
            configuration.setAllowCredentials(true);

            //Áp dụng cấu hình CORS cho tất cả các API endpoint (/**)
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        @Bean
        JwtDecoder jwtdecoder() {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            return NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }


}
