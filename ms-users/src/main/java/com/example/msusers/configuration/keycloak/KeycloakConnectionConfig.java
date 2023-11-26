package com.example.msusers.configuration.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConnectionConfig {

    @Value("${users.elves.keycloak-lothlorien.serverUrl}")
    private String serverUrl;
    @Value("${users.elves.keycloak-lothlorien.clientId}")
    private String clientId;
    @Value("${users.elves.keycloak-lothlorien.clientSecret}")
    private String clientSecret;
    @Value("${users.elves.keycloak-lothlorien.realm}")
    private String realm;

    @Bean
    public Keycloak getInstance(){
        return KeycloakBuilder.builder()
                .serverUrl(this.serverUrl)
                .realm(this.realm)
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
