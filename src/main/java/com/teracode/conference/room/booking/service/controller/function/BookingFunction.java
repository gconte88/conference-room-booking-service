package com.teracode.conference.room.booking.service.controller.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import com.teracode.conference.room.booking.service.common.BookingDTO;
import com.teracode.conference.room.booking.service.domain.model.Booking;

/**
 * @author gon
 */
public class BookingFunction implements Function<Booking, BookingDTO> {

  public static BookingFunction INSTANCE = new BookingFunction();

  private SimpleDateFormat formatDate = new SimpleDateFormat("kk:mm");

  @Override
  public BookingDTO apply(Booking booking) {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //we dont care if its the start or end, we just want the day.
    Instant instant = booking.getStartDate().toInstant(ZoneOffset.UTC);
    Date date = Date.from(instant);
    return new BookingDTO(booking.getId(), RoomFunction.INSTANCE.apply(booking.getRoom()),
        UserFunction.INSTANCE.apply(booking.getUser()),
        this.getReservedMinutes(booking.getStartDate(), booking.getEndDate()), formatter.format(date));
  }

  /**
   * @param startDate
   * @param endDate
   * @return {@link List} of {@link String} with the minutes to be disabled (already reserved)
   */
  private List<String> getReservedMinutes(LocalDateTime startDate, LocalDateTime endDate) {

    List<String> reservedMinutes = new ArrayList<>();
    if (startDate != null && endDate != null) {
      reservedMinutes.add(formatLocalDateToDateString(startDate));
      reservedMinutes.add(formatLocalDateToDateString(endDate));
    }

    //sorted
    reservedMinutes.sort(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        try {
          return formatDate.parse(o1).compareTo(formatDate.parse(o2));
        } catch (ParseException e) {
          return 0;
        }
      }
    });

    return reservedMinutes;
  }

  private String formatLocalDateToDateString(LocalDateTime localDateTime) {
    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    Date date = Date.from(instant);
    return formatDate.format(date).toLowerCase();
  }
}
