package com.alichan.hostnavi.admin.application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserUserMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUser;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUserExample;

@Service
public class AuthUserService implements UserDetailsService {
  @Autowired
  private UserUserMapper userUserMapper;

  @Override
  public AuthUser loadUserByUsername(String mail) throws UsernameNotFoundException {
    UserUserExample userUserExample = new UserUserExample();
    userUserExample.createCriteria().andMailEqualTo(mail).andDeleteFlagEqualTo(false);
    UserUser user = userUserMapper.selectByExample(userUserExample).get(0);

    AuthUser authUser = new AuthUser();
    authUser.setId(user.getId());
    authUser.setMail(user.getMail());
    authUser.setPassword(user.getPassword());

    return authUser;
  }
}
