package com.kowal.photographer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests()
                .antMatchers("/panel", "/timetable/view").hasAnyRole("USER","ADMIN")
                .antMatchers("/timetable/confirm",
                        "/panel/*",
                        "/timetable/delete",
                        "/timetable/list",
                        "/problem/list",
                        "/problem/resolve",
                        "/timetable/add-photo",
                        "/page-settings").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/login")
                .and()
                .build();
    }
}
