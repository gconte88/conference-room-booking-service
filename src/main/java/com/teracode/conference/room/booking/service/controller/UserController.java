package com.teracode.conference.room.booking.service.controller;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.auth.SecurityService;
import com.teracode.conference.room.booking.service.auth.UserService;
import com.teracode.conference.room.booking.service.auth.UserValidator;
import com.teracode.conference.room.booking.service.domain.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author gon
 */
@Controller
public class UserController {

  @Resource
  private UserService userService;

  @Resource
  private SecurityService securityService;

  @Resource
  private UserValidator userValidator;

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());

    return "registrationView";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return "registrationView";
    }

    userService.save(userForm);

    securityService.autoLogin(userForm.getUserName(), userForm.getPasswordConfirm());

    return "redirect:/welcome";
  }

  @GetMapping({"/login", "/"})
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");


    return "login";
  }

  @GetMapping("/welcome")
  public String welcome(Model model) {
    return "welcome";
  }
}
