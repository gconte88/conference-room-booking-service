package com.teracode.conference.room.booking.service.api;

import java.util.List;
import java.util.Set;

import com.teracode.conference.room.booking.service.common.BookingDTO;
import com.teracode.conference.room.booking.service.common.RoomDTO;

/**
 * @author gon
 */
public interface ConferenceRoomBookingService {

  /**
   * @return a {@link List} of {@link RoomDTO} with the available rooms
   */
  List<RoomDTO> getRooms();

  /**
   * @return a {@link List} of {@link BookingDTO} of a room
   */
  Set<BookingDTO> getBookingsOfRoom(long roomId);

  /**
   * @param roomId
   * @param date
   * @param startDate
   * @param endDate
   * @return {@link BookingDTO} with the book of the room
   */
  BookingDTO BookRoom(long roomId, String date, String startDate, String endDate);

  /**
   * @param bookingId
   * @param startDate
   * @param endDate
   * @return {@link BookingDTO} with the edited schedule
   */
  BookingDTO editBookingSchedule(long bookingId, String startDate, String endDate);

  /**
   * @param bookingId
   */
  void cancelBooking(long bookingId);

  /**
   * @param bookingId
   * @return {@link Set} of {@link BookingDTO}
   */
  Set<BookingDTO> getAllBookingsForUser(long bookingId);


}
