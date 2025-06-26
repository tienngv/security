package com.tienngv.security.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Username không được để trống")
    private String username;
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    @NotBlank(message = "Password không được để trống")
    private String password;
}
