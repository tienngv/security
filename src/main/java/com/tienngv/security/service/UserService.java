package com.tienngv.security.service;

import com.tienngv.security.request.UserDto;

public interface UserService {
    UserDto findByUsername(String username);
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto, Long id);
}
