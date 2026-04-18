package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login.html",
                                "/login",
                                "/logout",
                                "/error",
                                "/images/**",
                                "/favicon.ico"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/building", "/api/building/*")
                        .hasAnyRole("MANAGER", "STAFF", "CUSTOMER")

                        .requestMatchers(HttpMethod.POST, "/api/building").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/building/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/building/*").hasRole("MANAGER")

                        .requestMatchers("/api/users/**").hasRole("MANAGER")
                        .requestMatchers("/api/staff/**").hasAnyRole("STAFF", "MANAGER")

                        .requestMatchers("/admin/**").hasRole("MANAGER")
                        .requestMatchers("/staff/**").hasAnyRole("STAFF", "MANAGER")
                        .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "MANAGER")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .successHandler(customLoginSuccessHandler)
                        .failureUrl("/login.html?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}