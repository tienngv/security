package com.tienngv.security.controller;

import com.tienngv.security.request.UserDto;
import com.tienngv.security.service.UserService;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable
                              @NotNull(message = "Id must not empty") Long id,
                              @RequestBody @Valid UserDto userDto) {
        return userService.update(userDto, id);
    }

    @GetMapping("/abc")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> abc() {
        Map<String,String> rs = new HashMap<>();
        rs.put("mess","Hello World");
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/abcd")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> abcdE() {
        Map<String,String> rs = new HashMap<>();
        rs.put("mess","Hello World");
        return ResponseEntity.ok(rs);
    }
}
