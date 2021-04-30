package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegistrationDAO extends JpaRepository<User, Long> {
	User findByUsername(String Username);
	User findByEmail(String Email);
	User findByPhonenumber(String Phonenumber);



}