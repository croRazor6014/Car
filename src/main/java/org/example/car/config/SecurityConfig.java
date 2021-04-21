package org.example.car.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@EnableWebSecurity
public class SecurityConfig {

  @Configuration
  @Order(1)
  public static class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean authenticationFilterRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean(authenticationFilter());
      registration.setEnabled(false);
      return registration;
    }

    @Bean
    public GenericFilterBean authenticationFilter() {

      return new AuthenticationFilter();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http
          .cors().disable()
          .csrf().disable()
          .antMatcher("/api/**")
          .addFilterAfter(authenticationFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
      web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
  }
}
