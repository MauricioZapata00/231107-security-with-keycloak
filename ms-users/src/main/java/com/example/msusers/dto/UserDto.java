package com.example.msusers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String id;
    private String userName;
    private String email;
    private String firstName;
    private List<BillDto> bills;
}
