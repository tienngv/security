package com.tienngv.security.service;

import com.tienngv.security.request.Address;
import com.tienngv.security.request.UserDto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<UserDto> listUserDto = List.of(
                new UserDto("Tung","tienok",new Address("Hn","Haha","Hihi")),
                new UserDto("Tung","sahurOk",new Address("Abc","Haha","Hihi")),
                new UserDto("Hihi","hihiO2",new Address("Hn","Haha","Hihi"))
        );
        Map<String, List<UserDto>> userTerminalMap = listUserDto
                .stream()
                .collect(Collectors.groupingBy(UserDto::getFullName));
        System.out.println(userTerminalMap);

        userTerminalMap.forEach((userName, ls)->{
            System.out.println(userName);
        });
    }

}
