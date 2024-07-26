package com.bookyourhotel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

    private JwtConfig jc;

    public SecurityConfig(JwtConfig jc) {
        this.jc = jc;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jc, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
//                .requestMatchers("/api/v1/appUser/addUsers" , "/api/v1/appUser/verifyUser").permitAll()
//                .requestMatchers("/api/v1/Property/addProperty" , "/api/v1/Country/addCountry" , "/api/v1/Location/addLocation")
//                .hasRole("ADMIN")
//                .anyRequest()
//                .authenticated();
        return http.build();
    }
}
