package com.alichan.hostnavi.admin.application.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // csrfの無効化
    http.csrf().ignoringAntMatchers("/**");
    // OPTIONSメソッドへのCORSの無効化
    http.cors();
    // ログインが必須なページを修正
    http.authorizeHttpRequests(auth -> {
      auth.antMatchers("/users/login").permitAll();
      auth.antMatchers(HttpMethod.POST, "/users").permitAll();
      auth.antMatchers(HttpMethod.PUT, "/users/{\\d+}").permitAll();
      auth.antMatchers(HttpMethod.POST, "/users/image/{\\d+}").permitAll();
      auth.antMatchers("/credit-cards/**").authenticated();
      auth.antMatchers("/reservations/**").authenticated();
      auth.antMatchers("/reviews/**").authenticated();
      auth.antMatchers("/messages/**").authenticated();
      auth.antMatchers("/inns/**").authenticated();
      // テスト用に認証を外す時に使用する
      // auth.antMatchers("/inns/**").permitAll();
      auth.antMatchers("/faclities/**").authenticated();
      auth.antMatchers("/amenities/**").authenticated();
      auth.antMatchers("/views/**").authenticated();
      auth.antMatchers(HttpMethod.DELETE, "/users/{\\d+}").authenticated();
    });
    // 認証Filterを設定
    http.addFilter(new JWTAuthenticationFilter(
        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));
    // 認可Filterを設定
    http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
