package com.workintech.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();

                    auth.requestMatchers(HttpMethod.GET,"/product/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.POST,"/product/**").hasAnyAuthority("ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.PUT,"/product/**").hasAnyAuthority("ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.DELETE,"/product/**").hasAnyAuthority("ADMIN","SUPPLIER");

                    auth.requestMatchers(HttpMethod.GET,"/category/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.POST,"/category/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/category/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/category/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/chart/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.POST,"/chart/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/chart/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/chart/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/order/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.POST,"/order/**").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/order/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/order/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/user/**").hasAuthority("ADMIN");

                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
