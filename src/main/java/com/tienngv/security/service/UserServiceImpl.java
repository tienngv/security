package com.tienngv.security.service;

import com.tienngv.security.entity.User;
import com.tienngv.security.exception.GpayException;
import com.tienngv.security.repository.UserRepository;
import com.tienngv.security.request.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new GpayException("400", "Không tồn tại user");
        }
        modelMapper.map(userDto, user);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username, username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                AuthorityUtils.NO_AUTHORITIES);
    }
}
