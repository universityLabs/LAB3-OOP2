package com.tech.zootech.security.security;

import com.tech.zootech.security.filter.CustomAuthorizationFilter;
import com.tech.zootech.security.utils.contracts.AlgorithmUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AlgorithmUtil algorithmUtil;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests().requestMatchers("/**").permitAll() // for develop usage
                .and()
                .authorizeHttpRequests().requestMatchers("/api/login/**","/api/token/refresh/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER")
                .and()
                .authorizeHttpRequests().requestMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN")
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(new CustomAuthorizationFilter(algorithmUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
