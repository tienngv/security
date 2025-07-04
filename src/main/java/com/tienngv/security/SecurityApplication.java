package com.tienngv.security;


import com.tienngv.security.entity.User;
import com.tienngv.security.request.UserDto;
import com.tienngv.security.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "VietTien Blog API",
                version = "1.0.0",
                description = "RESTful API for managing blog content and users",
                termsOfService = "https://viettien.dev/terms",
                contact = @Contact(
                        name = "Nguyen Gia Viet Tien",
                        email = "viettien@example.com",
                        url = "https://viettien.dev"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
@SpringBootApplication
@RequiredArgsConstructor
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}


