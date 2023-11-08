package com.digitalhouse.msgateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;



@Configuration
public class DoorsOfDurinConfig {

    @Bean
    public SecurityWebFilterChain openDoorsOfRealm(ServerHttpSecurity httpSecurity){
        httpSecurity
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .anyExchange().authenticated());
        return addOAuth2LoginConfiguration(httpSecurity).build();
    }

    private ServerHttpSecurity addOAuth2LoginConfiguration(ServerHttpSecurity httpSecurityMissingForOAuth){
        return httpSecurityMissingForOAuth.oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec
                .authenticationSuccessHandler((webFilterExchange, authentication) -> webFilterExchange.getChain()
                        .filter(webFilterExchange.getExchange())
                        .then()));
    }
}
