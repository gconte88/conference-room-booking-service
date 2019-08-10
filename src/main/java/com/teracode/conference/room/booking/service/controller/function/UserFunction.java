package com.teracode.conference.room.booking.service.controller.function;

import java.util.function.Function;

import com.teracode.conference.room.booking.service.common.UserDTO;
import com.teracode.conference.room.booking.service.domain.model.User;

/**
 * @author gon
 */
public class UserFunction implements Function<User, UserDTO>{

  public static UserFunction INSTANCE = new UserFunction();
  @Override
  public UserDTO apply(User user) {
    return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
  }
}
