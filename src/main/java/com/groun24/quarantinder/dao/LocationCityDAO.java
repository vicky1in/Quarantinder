package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationCityDAO extends JpaRepository<Location, Long> {
	Location findByCity(String city);
}