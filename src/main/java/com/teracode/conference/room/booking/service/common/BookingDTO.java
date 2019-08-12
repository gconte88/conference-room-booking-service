package com.teracode.conference.room.booking.service.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

  @JsonProperty(value = "reserved_minutes")
  private List<String> reservedMinutes;

  @JsonProperty(value = "reserved_date")
  private String reservedDate;

}
