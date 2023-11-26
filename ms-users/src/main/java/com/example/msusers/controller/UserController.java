package com.example.msusers.controller;

import com.example.msusers.client.BillClient;
import com.example.msusers.domain.User;
import com.example.msusers.dto.BillDto;
import com.example.msusers.dto.UserDto;
import com.example.msusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BillClient billClient;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.buildBodyForResponse());
    }

    private List<UserDto> buildBodyForResponse(){
        return this.billClient.getListOfBills().getBody().stream()
                .map(this::assignBillToUser)
                .toList().stream().flatMap(List::stream).toList();
    }

    private List<UserDto> assignBillToUser(BillDto billDtoToAssign){
        return this.userService
                .getAllUsers().stream()
                .map(this::mapUserModelToDto)
                .map(userDto -> this.verifyBillsForUser(userDto, billDtoToAssign))
                .toList();
    }

    private UserDto mapUserModelToDto(User userToMap){
        UserDto dtoToReturn = new UserDto();
        dtoToReturn.setId(userToMap.getId());
        dtoToReturn.setUserName(userToMap.getUserName());
        dtoToReturn.setEmail(userToMap.getEmail());
        dtoToReturn.setFirstName(userToMap.getFirstName());
        return dtoToReturn;
    }

    private UserDto verifyBillsForUser(UserDto userDto, BillDto billDto){
        return Objects.equals(userDto.getId(), billDto.getCustomerBill()) ?
                this.addBillToUser(userDto, billDto) : userDto;
    }

    private UserDto addBillToUser(UserDto userDto, BillDto billDto){
        List<BillDto> userBills = userDto.getBills();
        userBills.add(billDto);
        userDto.setBills(userBills);
        return userDto;
    }
}
