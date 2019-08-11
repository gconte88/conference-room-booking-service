package com.teracode.conference.room.booking.service.auth;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author gon
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

  @Resource
  private AuthenticationManager authenticationManager;

  @Resource
  private UserDetailsService userDetailsService;

  @Override
  public String findLoggedInUsername() {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (userDetails instanceof UserDetails) {
      return ((UserDetails) userDetails).getUsername();
    }

    return null;
  }

  @Override
  public boolean autoLogin(String username, String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

    Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authenticate);
      log.debug(String.format("Auto login %s successfully!", username));
      return true;
    }

    return false;
  }
}
