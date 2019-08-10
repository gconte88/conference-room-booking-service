package com.teracode.conference.room.booking.service.controller.function;

import java.util.function.Function;

import com.teracode.conference.room.booking.service.common.BookingDTO;
import com.teracode.conference.room.booking.service.domain.model.Booking;

/**
 * @author gon
 */
public class BookingFunction implements Function<Booking, BookingDTO> {

  public static BookingFunction INSTANCE = new BookingFunction();

  @Override
  public BookingDTO apply(Booking booking) {
    return new BookingDTO(booking.getId(), RoomFunction.INSTANCE.apply(booking.getRoom()),
        UserFunction.INSTANCE.apply(booking.getUser()), booking.getStartDate(), booking.getEndDate());
  }
}
