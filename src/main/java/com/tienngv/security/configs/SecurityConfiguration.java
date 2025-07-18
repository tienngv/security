package com.tienngv.security.configs;

import com.tienngv.security.exception.ErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.Base64;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ErrorHandler errorHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(customizer -> {
                })
                .httpBasic(customizer -> {
                })
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(errorHandler) // <-- Sử dụng entry point custom
                )
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                        .requestMatchers(HttpMethod.GET,"/ReturnUrl","/vnpay-return").permitAll()
                        .anyRequest()
                        .authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        String userName = "user";
        String password = "0a2feeec-dfca-45dd-a278-c6e8be4de9c1";
        String ok = userName + ":" + password;

        String encoded = Base64.getEncoder().encodeToString(ok.getBytes());
        System.out.println("Basic " + encoded);
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println(new String(decoded));

    }
}
