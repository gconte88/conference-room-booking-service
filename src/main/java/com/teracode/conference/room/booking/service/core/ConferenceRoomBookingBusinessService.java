package com.teracode.conference.room.booking.service.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.teracode.conference.room.booking.service.domain.model.Booking;
import com.teracode.conference.room.booking.service.domain.model.Room;

/**
 * @author gon
 */
public interface ConferenceRoomBookingBusinessService {

  public List<Room> getRooms();

  public Set<Booking> getBookingsOfRoom(long roomId);

  public Booking BookRoom(long roomId, String userId, LocalDateTime startDate, LocalDateTime endDate);

  public Booking editBookingSchedule(long bookingId, LocalDateTime startDate, LocalDateTime endDate);

  public void cancelBooking(long bookingId);

  public Set<Booking> getAllBookingsForUser(long userId);


}
