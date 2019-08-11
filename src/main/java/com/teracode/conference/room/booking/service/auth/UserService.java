package com.teracode.conference.room.booking.service.auth;

import com.teracode.conference.room.booking.service.domain.model.User;

/**
 * @author gon
 */
public interface UserService {

  void save(User user);

  User findByUserName(String username);
}
