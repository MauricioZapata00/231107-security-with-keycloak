package com.example.msusers.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(toBuilder = true)
@Getter
public class User {
    //esta entidad la modelan ustedes de acuerdo a los atributos que vayan a necesitar
    private String id;
    private String userName;
    private String email;
    private String firstName;
    private List<Bill> bills;
}
