package com.example.msusers.repository;


import com.example.msusers.domain.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserRepository{

    private final Keycloak keycloakClient;

    @Value("${usuarios.keycloak.realm}")
    private String realm;
    @Override
    public List<User> findAllUsers() {
        return keycloakClient.realm(realm).users().list()
                .stream().map(this::mapKeycloackUserToDomainUser)
                .toList();
    }

    private User mapKeycloackUserToDomainUser(UserRepresentation userRepresentation){
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .build();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return keycloakClient.realm(realm).users().list()
                .stream().filter(userRepresentation -> userRepresentation.getId().equals(id))
                .findFirst().map(this::mapKeycloackUserToDomainUser);
    }

}
