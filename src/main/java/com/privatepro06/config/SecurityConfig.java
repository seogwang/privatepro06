package com.privatepro06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((aurhorizeHttpRequests) -> aurhorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        http.formLogin((formLogin) -> formLogin
                .loginPage("/member/login")
                .loginProcessingUrl("/member/loginPro")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login"));

        http.logout((logout) -> logout
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/"));

        return http.build();
    }
}
