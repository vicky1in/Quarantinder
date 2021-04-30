package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long>{
}