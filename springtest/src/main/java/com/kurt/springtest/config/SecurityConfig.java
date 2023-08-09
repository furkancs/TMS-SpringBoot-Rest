package com.kurt.springtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kurt.springtest.security.JwtAuthenticationEntryPoint;
import com.kurt.springtest.security.JwtAuthenticationFilter;
import com.kurt.springtest.service.UserDetailsServiceImpl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint handler;
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 
    	return http
    			.cors(cor -> cor.configure(http))
    			.csrf(csrf -> csrf.disable())
    			.authorizeHttpRequests(auth -> {
    				//auth.requestMatchers(/users").permitAll();
    				auth.requestMatchers("/auth/**").permitAll();
    				auth.anyRequest().permitAll();
    			})
    			.exceptionHandling(exc -> exc.authenticationEntryPoint(handler))
    			.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.httpBasic(Customizer.withDefaults())
    			.build();
    }
}
