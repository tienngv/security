package com.tienngv.security.controller;

import com.tienngv.security.request.UserDto;
import com.tienngv.security.service.UserService;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable
                              @NotNull(message = "Id must not empty") Long id,
                              @RequestBody @Valid UserDto userDto) {
        return userService.update(userDto, id);
    }

    @GetMapping("/{user-name}")
    public UserDto getUser(@PathVariable(value = "user-name")
                           @NotNull(message = "Id must not empty") String userName) {
        return userService.findByUsername(userName);
    }
}
