package com.teracode.conference.room.booking.service.common;

import java.io.Serializable;

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
public class RoomDTO implements Serializable {

  private static final long serialVersionUID = -3211006870285217704L;

  @JsonProperty(value = "id")
  private long id;

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "seats")
  private int seats;
}
