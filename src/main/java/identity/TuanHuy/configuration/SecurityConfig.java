    package identity.TuanHuy.configuration;
    import lombok.RequiredArgsConstructor;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
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
    import org.springframework.web.cors.CorsConfigurationSource;
    import javax.crypto.spec.SecretKeySpec;


    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {


        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        private final CorsConfigurationSource corsConfigurationSource;


        @Value("${spring.jwt.signerKey}")
        private String signerKey;


        private final String[] SWAGGER_WHITELIST={
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**"
        };


        private final String[] PUBLIC_ENDPOINTS_API_AUTHENTICATION = {
                "/api/v1/authentication/login",
                "/api/v1/authentication/introspect",
                "/api/v1/cloudinary/image/upload",
                "/api/v1/genres",
        };

        private final String[] PUBLIC_ENDPOINTS_API_USER = {
                "/api/v1/users" ,
                "/api/v1/users/{userId}/role" ,
                "/api/v1/users/{userId}"
        };

        private final String[] PUBLIC_ENDPOINTS_API_ROLE = {
                "/api/v1/roles",
                "/api/v1/roles/{roleName}",
                "/api/v1/roles/*",
                "/api/v1/roles/{roleName}/details"
        };

        private final String[] PUBLIC_ENDPOINTS_API_PERMISSION = {
                "/api/v1/permissions/*",
                "/api/v1/permissions"

        };

        private final String[] PUBLI_ENDPOINTS_API_KAFKA = {
                "/api/v1/kafka/publish/",
                "/api/v1/kafka/publish/**",
        };

        private final String[] PUBLI_ENDPOINTS_API_KAFKA_JSON = {
                "/api/v1/kafka/publish/",
                "/api/v1/kafka/publish/**",
        };

        private final String[] PUBLI_ENDPOINTS_API_CONVERSION = {
                "/api/v1/convert/**",
                "/api/v1/convert",
                "/api/v1/download/**",
                "/api/v1/health/**",
                "/api/v1/health",
        };
        private final String[] PUBLI_ENDPOINTS_API_PODCASTSERIES = {
                "/api/v1/podcastSeries/**",
                "/api/v1/podcastSeries"
        };

        private final String[] PUBLIC_ENDPOINTS_UI = {"/ui/home"};

        private Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers(SWAGGER_WHITELIST).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_UI).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_USER).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_AUTHENTICATION).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_ROLE).permitAll()
                            .requestMatchers(PUBLI_ENDPOINTS_API_KAFKA).permitAll()
                            .requestMatchers(PUBLI_ENDPOINTS_API_KAFKA_JSON).permitAll()
                            .requestMatchers(PUBLI_ENDPOINTS_API_CONVERSION).permitAll()
                            .requestMatchers(PUBLIC_ENDPOINTS_API_PERMISSION).authenticated()
                            .requestMatchers(PUBLI_ENDPOINTS_API_PODCASTSERIES).permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(exception -> exception.authenticationEntryPoint(customAuthenticationEntryPoint))
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtdecoder())))
                    .csrf(csrf -> csrf.disable()); // Không cần gọi `.cors()` nữa

            LOGGER.info("SecurityFilterChain initialized successfully by LOGGER FACTORY");
            return httpSecurity.build();
        }


        @Bean
        JwtDecoder jwtdecoder() {
            if (signerKey == null || signerKey.isEmpty()) {
                throw new IllegalArgumentException("JWT signerKey không được null hoặc rỗng");
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            return NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }




}
