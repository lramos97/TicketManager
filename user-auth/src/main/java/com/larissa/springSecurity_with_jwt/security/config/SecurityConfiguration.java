package com.larissa.springSecurity_with_jwt.security.config;

import com.larissa.springSecurity_with_jwt.security.authentication.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/movies").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/movies/{id}").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/movies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/movies/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/sessions").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/sessions/{id}").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/sessions").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sessions/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/tickets").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/tickets").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/tickets/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tickets/session/{sessionId}").permitAll()

                        .anyRequest().permitAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
