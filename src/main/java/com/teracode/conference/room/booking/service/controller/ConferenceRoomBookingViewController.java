package com.teracode.conference.room.booking.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @GetMapping(value = "/room/{id}")
  public String room(@PathVariable("id") int id, Model model) {
    model.addAttribute("id", id);
    return "room";
  }
}
