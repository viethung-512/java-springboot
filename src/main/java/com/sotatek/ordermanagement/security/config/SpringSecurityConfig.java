package com.sotatek.ordermanagement.security.config;


import com.sotatek.ordermanagement.security.CustomAuthenticationEntryPoint;
import com.sotatek.ordermanagement.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity httpSecurity, final JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(
                (authorize) ->
                        authorize
                                .requestMatchers("/v1/users/login")
                                .permitAll()
                                .requestMatchers("/v1/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll());
        httpSecurity.exceptionHandling(
                config -> config.authenticationEntryPoint(this.customAuthenticationEntryPoint));
        return httpSecurity.build();
    }
}
