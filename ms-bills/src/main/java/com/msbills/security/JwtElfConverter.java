package com.msbills.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

public class JwtElfConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {

        Map<String, Object> realmRolesAccess = source.getClaim("realm_access");
        List<GrantedAuthority> authorities = new ArrayList<>(addFeatureToElfJwt(realmRolesAccess));
        authorities.addAll(this.addRaceToElfJWT(source));
        return authorities;
    }

    private List<SimpleGrantedAuthority> addFeatureToElfJwt(Map<String, Object> realmRolesAccess) {
        return ((List<String>) realmRolesAccess.get("roles"))
                .stream().map(roleMap -> "LEFT_BIG_EAR_" + roleMap + "_RIGHT_BIG_EAR")
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private List<SimpleGrantedAuthority> addRaceToElfJWT(Jwt token){
        return ((List<String>) token.getClaim("race"))
                .stream().map(elfRace -> "ELVES_RACE" + elfRace)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }


}
