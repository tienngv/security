package com.tienngv.security.controller;

import com.tienngv.security.exception.GpayException;
import com.tienngv.security.request.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class TestController {

//    @PostMapping("v1/api/user")
//    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
//        if ("USER".equals(userDto.getUsername())) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("username", userDto.getUsername());
//            map.put("mes", "Đã tồn tại user này");
//            throw new GpayException("400", "UIAUIA UiiiA", map);
//        }
//        if (Objects.isNull(userDto.getAddress())) {
//            throw new GpayException("JQK123","Address can't be null");
//        }
//        System.out.println(userDto.getAddress().getCity());
//
//        return userDto;
//    }
}
