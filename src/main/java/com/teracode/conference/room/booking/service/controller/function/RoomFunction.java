package com.teracode.conference.room.booking.service.controller.function;

import java.util.function.Function;

import com.teracode.conference.room.booking.service.common.RoomDTO;
import com.teracode.conference.room.booking.service.domain.model.Room;

/**
 * @author gon
 */
public class RoomFunction implements Function<Room, RoomDTO> {

  public static RoomFunction INSTANCE = new RoomFunction();
  @Override
  public RoomDTO apply(Room room) {
    return new RoomDTO(room.getId(), room.getName(), room.getSeats());
  }
}
