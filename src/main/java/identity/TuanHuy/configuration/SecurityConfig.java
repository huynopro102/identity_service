    package identity.TuanHuy.configuration;
    import org.springframework.beans.factory.annotation.Autowired;
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
    import javax.crypto.spec.SecretKeySpec;


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

        private final String[] PUBLIC_ENDPOINTS_API = { "/api/authentication/login",
                "/api/mienphi",
                "/api/cloudinary/image/upload",
                "/api/users" ,
                "/api/genres"
        };

        private final String[] PUBLIC_ENDPOINTS_API_ROLE = {
                "/api/roles",
                "/api/roles/{roleName}"

        };

        private final String[] PUBLIC_ENDPOINTS_UI = {"/ui/home"};

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity

                    // custom message error code 401
                    .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .and()


                    // Cho phép các endpoint công khai
                    .authorizeHttpRequests(request -> request


                            .requestMatchers(SWAGGER_WHITELIST).permitAll() // add whitelist for Swagger ui

                            .requestMatchers("/api/users").permitAll()  // allow user create account , Cho phép mọi method trên /api/users
                            .requestMatchers(HttpMethod.GET ,"/api/users/*").permitAll()  // allow user display information account
                            .requestMatchers(HttpMethod.GET ,"/api/genres/*").permitAll()  // allow user display information genre
                            .requestMatchers("/api/genres/*").permitAll()  // allow user curd genres
                            .requestMatchers("/ui/*").permitAll()  // allow user UI
                            .requestMatchers("/api/postCategories/*").permitAll()  // allow user curd categories
                            .requestMatchers("/api/postCategories").permitAll()  // allow user curd categories
                            .requestMatchers(PUBLIC_ENDPOINTS_UI).permitAll()  // UI công khai
                            .requestMatchers(PUBLIC_ENDPOINTS_API).permitAll()  // API công khai
                            .requestMatchers(PUBLIC_ENDPOINTS_API_ROLE).permitAll() // Api role
                            .anyRequest().authenticated()  // Mọi request khác cần xác thực
                    )
                    .csrf().disable(); // Tắt CSRF cho REST API

            // Chỉ áp dụng OAuth2 Resource Server cho API
            httpSecurity
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtdecoder()))
                    );

            return httpSecurity.build();
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
