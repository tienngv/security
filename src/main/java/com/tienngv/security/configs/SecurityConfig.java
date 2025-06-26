//package com.tienngv.security.configs;
//
//// Optional nếu muốn config rõ ràng
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/", "/login").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .oauth2Login();
////        return http.build();
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/public").permitAll()
//                        .requestMatchers("/api/user").hasRole("USER")
//                        .requestMatchers("/api/admin").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults()); // login page mặc định
//
//        return http.build();
//    }
//
//    // Giả lập user trong bộ nhớ
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("1")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin1")
//                .password("1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//}
