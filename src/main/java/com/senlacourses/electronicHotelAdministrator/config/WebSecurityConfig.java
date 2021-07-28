package com.senlacourses.electronicHotelAdministrator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsServiceImplementation;

  @Bean
  @Scope(value = "singleton")
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/registration", "/login").not().fullyAuthenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/residents/**").hasRole("USER")
            .antMatchers("/hotel-rooms/**").hasRole("USER")
            .antMatchers("/registration-cards/**").hasRole("USER")
            .antMatchers("/services/**").hasRole("USER")
          .anyRequest().authenticated()
          .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
          .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(bCryptPasswordEncoder());
  }
}