package com.teracode.conference.room.booking.service.domain.repository;

import com.teracode.conference.room.booking.service.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gonzalo Conte
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
