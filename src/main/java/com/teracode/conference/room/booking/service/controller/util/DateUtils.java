package com.teracode.conference.room.booking.service.controller.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author gon
 */
public class DateUtils {

  public static LocalDateTime formatLocalDateTimeString(String localDateTime) {

    Instant instant = Instant.parse(localDateTime);
    return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
  }
}
