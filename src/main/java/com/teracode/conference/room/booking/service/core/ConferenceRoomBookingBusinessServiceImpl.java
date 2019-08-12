package com.teracode.conference.room.booking.service.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.teracode.conference.room.booking.service.domain.model.Booking;
import com.teracode.conference.room.booking.service.domain.model.Room;
import com.teracode.conference.room.booking.service.domain.model.User;
import com.teracode.conference.room.booking.service.domain.repository.BookingRepository;
import com.teracode.conference.room.booking.service.domain.repository.RoomRepository;
import com.teracode.conference.room.booking.service.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gon
 */
@Slf4j
@Service
public class ConferenceRoomBookingBusinessServiceImpl implements ConferenceRoomBookingBusinessService {

  @Resource
  private BookingRepository bookingRepository;

  @Resource
  private RoomRepository roomRepository;

  @Resource
  private UserRepository userRepository;

  @Override
  public List<Room> getRooms() {
    log.info("Getting all Rooms");
    return this.roomRepository.findAll();
  }

  @Override
  public Set<Booking> getBookingsOfRoom(long roomId) {

    Room room = this.roomRepository.getOne(roomId);
    return room.getBookings();
  }

  @Override
  public Booking BookRoom(long roomId, String userName, LocalDateTime startDate, LocalDateTime endDate) {

    log.info("Creating a new booking for room: {}, user: {} with startDate: {}, and endDate:{}", roomId, userName,
        startDate, endDate);

    Room room = this.roomRepository.getOne(roomId);

    room.validateBooking(startDate, endDate, null);

    User user = this.userRepository.findByUserName(userName);

    Booking booking = new Booking(startDate, endDate);
    booking.setRoom(room);
    booking.setUser(user);

    this.bookingRepository.save(booking);

    return booking;
  }

  @Override
  public Booking editBookingSchedule(long bookingId, LocalDateTime startDate, LocalDateTime endDate) {

    log.info("Editing Booking Schedule for id: {}, startDate: {}, endDate:{}", bookingId, startDate, endDate);

    Booking booking = this.bookingRepository.getOne(bookingId);

    booking.getRoom().validateBooking(startDate, endDate, bookingId);

    booking.setStartDate(startDate);

    booking.setEndDate(endDate);

    this.bookingRepository.save(booking);

    return booking;
  }

  @Override
  public void cancelBooking(long bookingId) {
    log.info("Cancelling booking {}", bookingId);

    this.bookingRepository.deleteById(bookingId);
  }

  @Override
  public Set<Booking> getAllBookingsForUser(long userId) {
    log.info("Getting all bookings for user: {}", userId);

    return this.userRepository.getOne(userId).getBookings();
  }
}
