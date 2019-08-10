package com.teracode.conference.room.booking.service.domain.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Gonzalo Conte
 */
@Entity
@Table(name = "booking")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @ManyToOne
  @JoinTable(name = "user_booking_relation",
             joinColumns = {@JoinColumn(name="booking_id")},
             inverseJoinColumns = @JoinColumn(name = "user_id"))
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "room_booking_relation",
             joinColumns = {@JoinColumn(name="booking_id")},
             inverseJoinColumns = @JoinColumn(name = "room_id"))
  private Room room;

  public Booking(LocalDateTime startDate, LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void setRoom(Room room) {
    this.room = room;
    room.addBooking(this);
  }

  public void setUser(User user) {
    this.user = user;
    user.addBooking(this);
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}
