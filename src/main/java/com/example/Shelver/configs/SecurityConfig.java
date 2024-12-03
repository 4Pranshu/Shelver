package com.example.Shelver.configs;

import com.example.Shelver.models.Authority;
import com.example.Shelver.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public AuthenticationManager authenticationManager(UserService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf->csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/v1/students/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/api/v1/students/**").hasAuthority(Authority.STUDENT.name())
                        .requestMatchers("/api/v1/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/api/v1/books/**").hasAnyAuthority(Authority.ADMIN.name(),Authority.STUDENT.name())
                        .requestMatchers("/api/v1/books/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/api/v1/transactions/**").hasAuthority(Authority.STUDENT.name())
                .requestMatchers("/**").permitAll()


        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
