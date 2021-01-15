package com.rent.RentApp.configs.security;

import com.rent.RentApp.services.Auth.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecConfigs extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthService authService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder(8));

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/cars").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/users").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/specs").denyAll();
    http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll();
    http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/*").permitAll();
    http.authorizeRequests().antMatchers("/rentals").permitAll();
    http.authorizeRequests().antMatchers("/login").permitAll();
    http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
  }

}
