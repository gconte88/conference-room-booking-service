package com.teracode.conference.room.booking.service.auth;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.domain.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author gon
 */
@Component
public class UserValidator implements Validator {

  @Resource
  private UserService userService;

  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    User user = (User) o;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

    if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
      errors.rejectValue("userName", "Size.userForm.username");
    }

    if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
      errors.rejectValue("userName", "Size.userForm.username");
    }
    if (userService.findByUserName(user.getUserName()) != null) {
      errors.rejectValue("userName", "Duplicate.userForm.username");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "Size.userForm.password");
    }

    if (!user.getPasswordConfirm().equals(user.getPassword())) {
      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
    }
  }
}
