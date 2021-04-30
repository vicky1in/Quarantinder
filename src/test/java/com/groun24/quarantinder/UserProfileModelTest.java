package com.groun24.quarantinder;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;

import com.groun24.quarantinder.Services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;


@SpringBootTest
public class UserProfileModelTest {

    @Autowired
    private UserService userService;

    @Test
    public void getAndSetBio() throws Exception {
        userService.createDummy("Snoop Doggity Dog", "snoopdog420", "420@blaze.com", "snoopdog420", Date.valueOf("2000-11-01"), "Male", "Female", 18, 29, "69420");
        User snoop = userService.findByUsername("snoopdog420");
        UserProfile snoopProfile = snoop.getProfile();

        // Check if setting it will change
        snoopProfile.setBio("blaze it");

        // Make sure get and set bio work correctly
        assertEquals("blaze it", snoop.getProfile().getBio());
    }

}
