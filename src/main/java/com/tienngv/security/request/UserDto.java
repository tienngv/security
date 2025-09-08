package com.tienngv.security.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tienngv.security.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

    @NotBlank(message = "{user.fullName.NotBlank.message}")
    private String fullName;

    @NotBlank(message = "{user.name.NotBlank.message}")
    private String username;

    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    @NotBlank(message = "Password không được để trống")
    private String password;

//    @NotBlank(message = "Confirm Password không được để trống")
//    private String confirmPassword;

    @NotBlank(message = "Email không được để trống")
    @Length(min = 6,max = 50, message = "Email phải nằm trong khoảng 6 -> 50 kí tự")
    @Email(message = "Không đúng định dạng email")
    private String email;

    @Pattern(regexp = "ADMIN|USER|MANAGER"
            , message = "User role must be ADMIN, USER, MANAGER")
    @NotBlank(message = "Role không được để trống")
    private String role;

    private Address address;
//    private Long id;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    public UserDto(String fullName,String username,Address address){
        this.fullName = fullName;
        this.username = username;
        this.address = address;
    }
}
