package com.teracode.conference.room.booking.service.domain.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Gonzalo Conte
 */
@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

  public static final int MINIMUM_BOOKING_MINUTES = 15;
  public static final int MAXIMUM_BOOKING_MINUTES = 180;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "seats")
  @Size(min = 10, max = 100)
  private int seats;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private Set<Booking> bookings = new HashSet<>();

  /**
   * Validates that a booking does not overlap in the schedule of the other bookings of the room (not itself)
   * Validates that the start date and end date time diff is between 15 minutes and 3 hours
   *
   * @param startDate
   * @param endDate
   * @param bookingToFilter
   * @throws ValidationException if any validation is not satisfied
   */
  public void validateBooking(LocalDateTime startDate, LocalDateTime endDate, Long bookingToFilter) {

    Supplier<Stream<Booking>> supplier = () -> bookings.stream();
    boolean overlap;
    if (bookingToFilter != null) {
      overlap = supplier.get().filter(booking -> booking.getId().intValue() != bookingToFilter.intValue()).anyMatch(
          booking -> booking.getStartDate().compareTo(endDate) <= 0 && booking.getEndDate().compareTo(startDate) >= 0);
    } else {
      overlap = supplier.get().anyMatch(
          booking -> booking.getStartDate().compareTo(endDate) <= 0 && booking.getEndDate().compareTo(startDate) >= 0);
    }

    if (!bookings.isEmpty() && overlap) {
      throw new ValidationException("conference.room.service.overlapping.a.booking");
    }
    long minutes = ChronoUnit.MINUTES.between(startDate, endDate);

    if (minutes < MINIMUM_BOOKING_MINUTES || minutes > MAXIMUM_BOOKING_MINUTES) {
      throw new ValidationException("conference.room.service.booking.minutes.invalid");
    }
  }

  public void addBooking(Booking booking) {
    this.bookings.add(booking);
  }
}