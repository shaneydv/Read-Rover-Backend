package com.shaneydv.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/api/books/secure/**").authenticated()
                                .requestMatchers("/api/books/**", "/api/reviews/**").permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );

        http.cors(Customizer.withDefaults());
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
        Okta.configureResourceServer401ResponseBody(http);
        return http.build();
    }
}