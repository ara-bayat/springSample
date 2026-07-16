package com.learning.springSample.product.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("1"))
                .authorities("special","basic")
                .roles("superUser")
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("2"))
                .authorities("basic")
                .roles("basicUser")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/open").permitAll();
                    authorize.requestMatchers("/close").authenticated();
                    authorize.requestMatchers(HttpMethod.POST,"/product").authenticated();
                    authorize.requestMatchers(HttpMethod.GET,"/special").hasAnyAuthority("special");
                    authorize.requestMatchers(HttpMethod.GET,"/basic").hasAnyAuthority("special","basic");
                    authorize.requestMatchers("/product/").hasAnyRole("basicUser");
                    authorize.requestMatchers("/products").hasAnyRole("superUser");
                    authorize.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
