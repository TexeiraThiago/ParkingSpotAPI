package com.api.parking_control.config.security;

import com.api.parking_control.enums.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        return http
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // permite post e delete
                .authorizeHttpRequests(
        (authorizationConfig) -> {
            authorizationConfig
                    .requestMatchers(HttpMethod.GET,"/parking-spot/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/parking-spot").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE,"/parking-spot/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/user").permitAll()
                    .anyRequest()
                    .authenticated();
        }
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}

