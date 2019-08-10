package com.teracode.conference.room.booking.service.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gon
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO implements Serializable{

  private static final long serialVersionUID = -5485428330021390062L;

  @JsonProperty(value = "id")
  private long id;

  @JsonProperty(value = "room")
  private RoomDTO room;

  @JsonProperty(value = "user")
  private UserDTO user;

  @JsonProperty(value = "start_date")
  private LocalDateTime startDate;

  @JsonProperty(value = "end_date")
  private LocalDateTime endDate;

}
