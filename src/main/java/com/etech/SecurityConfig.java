package com.etech;

import com.etech.security.jwt.JwtConfigurer;
import com.etech.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private final static String ADMIN_ENDPOINT = "/admin/**";
    private final static String AUTHENTICATION_ENDPOINT = "/auth/login";
    private final static String PRODUCTS_ENDPOINT = "/products/**";
    private final static String CATEGORIES_ENDPOINT = "/categories/**";
    private final static String IMAGES_ENDPOINT = "/images/**";

    private final static String USERS_ENDPOINT = "/users/**";
    private final static String ORDERS_ENDPOINT = "/orders/**";

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTHENTICATION_ENDPOINT, PRODUCTS_ENDPOINT, CATEGORIES_ENDPOINT, IMAGES_ENDPOINT).permitAll()
                .antMatchers(USERS_ENDPOINT).hasRole("USER")
                .antMatchers(ORDERS_ENDPOINT).hasRole("MANAGER")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}