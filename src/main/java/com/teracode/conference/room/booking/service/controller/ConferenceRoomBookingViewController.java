package com.teracode.conference.room.booking.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gon
 */
@Controller
public class ConferenceRoomBookingViewController {

  @GetMapping(value = "/book")
  public String book(Model model) {
    return "book";
  }

  @GetMapping(value = "/room/{room_id}")
  public String room(@PathVariable("room_id") int room_id, ModelMap model) {
    model.addAttribute("room_id", room_id);
    return "room";
  }

  @GetMapping(value = "/confirm")
  public String confirm(Model model) {
    return "confirm";
  }
}
