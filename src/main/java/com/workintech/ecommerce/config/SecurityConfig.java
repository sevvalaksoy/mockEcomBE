package com.workintech.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
    public CorsConfigurationSource corsConfigurationSetting(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().configurationSource(corsConfigurationSetting());
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/register/user/**").permitAll();
                    auth.requestMatchers("/auth/register/admin/**").hasAuthority("ADMIN");
                    auth.requestMatchers("/auth/register/supplier/**").hasAuthority("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/product/**").permitAll();
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

                    auth.requestMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.POST,"/user/**").hasAnyAuthority("USER","ADMIN","SUPPLIER");
                    auth.requestMatchers(HttpMethod.PUT,"/user/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/user/**").hasAuthority("ADMIN");

                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
