package com.santander.proyectofinal.config;

import com.santander.proyectofinal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/helloadmin").hasRole("ADMIN")
                .antMatchers("/api/v1/flights/new").permitAll()
                .antMatchers("/api/v1/flights").permitAll()
                .antMatchers("/api/v1/flights/edit").permitAll()
                .antMatchers("/api/v1/flight-reservation/new").permitAll()
                .antMatchers("/api/v1/flight-reservation").permitAll()
                .antMatchers("/api/v1/flight-reservation/edit").permitAll()
                .antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
                .antMatchers("/authenticate").permitAll().anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(customJwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }
}
