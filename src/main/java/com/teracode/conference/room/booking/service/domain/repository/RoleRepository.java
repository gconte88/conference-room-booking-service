package com.teracode.conference.room.booking.service.domain.repository;

import com.teracode.conference.room.booking.service.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gon
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
