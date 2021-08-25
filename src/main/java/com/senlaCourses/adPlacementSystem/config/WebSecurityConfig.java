package com.senlaCourses.adPlacementSystem.config;

import com.senlaCourses.adPlacementSystem.config.jwt.JwtFilter;
import com.senlaCourses.adPlacementSystem.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring web security config.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;
  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  @Scope("singleton")
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .authorizeRequests()
        .antMatchers("/registration", "/lost-password").not().fullyAuthenticated()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/ads/**").hasRole("USER")
        .antMatchers("/messages/**").hasRole("USER")
        .antMatchers("/messages").hasRole("USER")
        .antMatchers("/profile/**").hasRole("USER")
        .antMatchers("/search/**").hasRole("USER")
        .antMatchers("/user/**").hasRole("USER")
      .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginPage("/login")
        .permitAll()
      .and()
        .logout()
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }
}
