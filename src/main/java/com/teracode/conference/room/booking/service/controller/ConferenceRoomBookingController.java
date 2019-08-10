package com.teracode.conference.room.booking.service.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.api.ConferenceRoomBookingService;
import com.teracode.conference.room.booking.service.common.BookingDTO;
import com.teracode.conference.room.booking.service.common.RoomDTO;
import com.teracode.conference.room.booking.service.controller.function.BookingFunction;
import com.teracode.conference.room.booking.service.controller.function.RoomFunction;
import com.teracode.conference.room.booking.service.controller.util.DateUtils;
import com.teracode.conference.room.booking.service.core.ConferenceRoomBookingBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author gon
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/booking", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConferenceRoomBookingController implements ConferenceRoomBookingService {

  @Resource
  private ConferenceRoomBookingBusinessService conferenceRoomBookingBusinessService;

  @GetMapping(value = "/rooms")
  @Override
  public List<RoomDTO> getRooms() {
    log.info("New Request to get Rooms");
    return this.conferenceRoomBookingBusinessService.getRooms().stream().map(room -> RoomFunction.INSTANCE.apply(room))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/room/{roomId}/bookings")
  @Override
  public Set<BookingDTO> getBookingsOfRoom(@PathVariable long roomId) {
    log.info("New Request to Bookings of Room with id: {}", roomId);
    return this.conferenceRoomBookingBusinessService.getBookingsOfRoom(roomId).stream().map(BookingFunction.INSTANCE)
        .collect(Collectors.toSet());
  }

  @PostMapping(value = "/room/{roomId}/user/{userId}/book")
  @Override
  public BookingDTO BookRoom(@PathVariable long roomId, @PathVariable long userId,
      @RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate) {
    log.info("New Request to Book Room: {}, for user: {}, from:{} to:{}", roomId, userId, startDate, endDate);

    return BookingFunction.INSTANCE.apply(this.conferenceRoomBookingBusinessService
        .BookRoom(roomId, userId, DateUtils.formatLocalDateTimeString(startDate),
            DateUtils.formatLocalDateTimeString(endDate)));
  }

  @PutMapping(value = "/{bookingId}")
  @Override
  public BookingDTO editBookingSchedule(@PathVariable long bookingId,
      @RequestParam(value = "start_date") String startDate, @RequestParam(value = "end_date") String endDate) {
    log.info("New Request to edit booking schedule for id: {}", bookingId);
    return BookingFunction.INSTANCE.apply(this.conferenceRoomBookingBusinessService
        .editBookingSchedule(bookingId, DateUtils.formatLocalDateTimeString(startDate),
            DateUtils.formatLocalDateTimeString(endDate)));
  }

  @DeleteMapping(value = "/{bookingId}")
  @Override
  public void cancelBooking(@PathVariable long bookingId) {
    log.info("New Request to cancel a booking with id: {}", bookingId);
    this.conferenceRoomBookingBusinessService.cancelBooking(bookingId);
  }

  @GetMapping(value = "/booked/user/{userId}")
  @Override
  public Set<BookingDTO> getAllBookingsForUser(@PathVariable long userId) {
    log.info("New Request to get all bookings for user: {}", userId);

    return this.conferenceRoomBookingBusinessService.getAllBookingsForUser(userId).stream()
        .map(booking -> BookingFunction.INSTANCE.apply(booking)).collect(Collectors.toSet());
  }
}
