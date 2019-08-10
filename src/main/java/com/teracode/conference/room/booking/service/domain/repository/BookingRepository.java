package com.teracode.conference.room.booking.service.domain.repository;

import java.util.List;

import com.teracode.conference.room.booking.service.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gonzalo Conte
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


  List<Booking> findByUser(long user);
}
