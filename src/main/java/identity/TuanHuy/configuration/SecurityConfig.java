    package identity.TuanHuy.configuration;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
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
        @Value("${spring.jwt.signerKey}")
        private String signerKey;
        private final String[] PUBLIC_ENDPOINTS = {"/users","/authentication/login"};
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                    httpSecurity.authorizeHttpRequests(request ->
                            request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS)
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated())
                            .csrf().disable();
                    httpSecurity.oauth2ResourceServer(oauth2 ->
                            oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtdecoder())));
            return httpSecurity.build();
        }


        @Bean
        JwtDecoder jwtdecoder(){
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),"HS256");
            return NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
    }

