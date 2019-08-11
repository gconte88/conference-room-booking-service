package com.teracode.conference.room.booking.service.auth;

/**
 * @author gon
 */
public interface SecurityService {

  String findLoggedInUsername();

  boolean autoLogin(String username, String password);
}
