package com.teracode.conference.room.booking.service.auth;

import java.util.Collections;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.domain.model.User;
import com.teracode.conference.room.booking.service.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author gon
 */
@Component
public class DBAuthenticationProvider implements AuthenticationProvider {

  @Resource
  private UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String username = auth.getName();
    String password = auth.getCredentials().toString();

    User user = userRepository.findByUserName(username);
    if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
      return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    } else {
      throw new BadCredentialsException("External system authentication failed");
    }
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }

}
