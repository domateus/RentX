package com.rent.RentApp.configs.security;

import com.rent.RentApp.configs.security.jwt.JwtAuthenticationEntryPoint;
import com.rent.RentApp.configs.security.jwt.JwtRequestFilter;
import com.rent.RentApp.services.Auth.AuthService;
import com.rent.RentApp.services.Auth.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecConfigs extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(new BCryptPasswordEncoder(8));
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/cars").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/users").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/specs").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.GET, "/*").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/authenticate").permitAll();
    http.authorizeRequests().antMatchers("/rentals").permitAll();
    http.authorizeRequests().antMatchers("/login").permitAll();
    http.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
