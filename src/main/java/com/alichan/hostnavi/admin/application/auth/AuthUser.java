package com.alichan.hostnavi.admin.application.auth;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Setter;

@Setter
public class AuthUser implements UserDetails {
  private Long id;
  private String mail;
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return mail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
