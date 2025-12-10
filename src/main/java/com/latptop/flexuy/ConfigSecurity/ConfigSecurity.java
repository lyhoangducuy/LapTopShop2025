package com.latptop.flexuy.ConfigSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.latptop.flexuy.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)->auth.
            requestMatchers("/*","/admin/login").permitAll()
            .requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated())
            .formLogin((login)->login.loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/admin",true)).logout(logout->logout.logoutUrl("/logout")
                .logoutSuccessUrl("/login"));
            
        return http.build();
    }
    
    @Bean
	WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers("/static/**","/assets/**");
	}
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
	public AuthenticationManager authManager(HttpSecurity http, CustomUserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder()) // <-- đây quan trọng
			.and()
			.build();
	}
}
