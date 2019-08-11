package com.teracode.conference.room.booking.service.auth;

import java.util.HashSet;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.domain.model.User;
import com.teracode.conference.room.booking.service.domain.repository.RoleRepository;
import com.teracode.conference.room.booking.service.domain.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author gon
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserRepository userRepository;
  @Resource
  private RoleRepository roleRepository;
  @Resource
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet<>(roleRepository.findAll()));
    userRepository.save(user);
  }

  @Override
  public User findByUserName(String username) {
    return userRepository.findByUserName(username);
  }

}
