package com.groun24.quarantinder;

import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.Services.UserProfileService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserProfileServiceTest {
    
    @Autowired
    private UserProfileService userProfileService;

    @Test
    public void getAndSetProfile() throws Exception {
        UserProfile donald = new UserProfile();
        donald.setBio("STOP THE COUNT!!!");
        donald.setPhoto("orangeface");
        userProfileService.save(donald);

        UserProfile newDonald = userProfileService.get(9);

        // Make sure the profile gotten using service is the same
        assertEquals("STOP THE COUNT!!!", newDonald.getBio());
    }
}
