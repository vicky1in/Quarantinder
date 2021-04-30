package com.groun24.quarantinder.Services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StartupManager {

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagManager tagManager;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void startup() {

        // Populate location table from csv  
        locationService.populate(System.getProperty("user.dir")+"/src/main/java/com/groun24/quarantinder/australia_cities.csv");

        // Create dummy users
        // Format: userService.createDummy(name, username, email, password, birthday, gender, gender preference, min age, max age, phone number);
        userService.createDummy("Mark", "mark", "mark@email.com", "mark", Date.valueOf("2000-11-01"), "Male", "Female", 18, 35, "694220");
        userService.createDummy("Anthony", "ant", "ant@email.com", "ant", Date.valueOf("1990-11-01"), "Male", "Male", 18, 100, "13337");
        userService.createDummy("Joseph", "joe", "joe@email.com", "joe", Date.valueOf("1980-11-01"), "Male", "No preference", 30, 100, "694420");
        userService.createDummy("Daniel", "dan", "dan@email.com", "dan", Date.valueOf("1970-11-01"), "Male", "No preference", 18, 100, "13537");
        userService.createDummy("Julia", "jules", "jules@email.com", "jules", Date.valueOf("2000-11-05"), "Female", "Male", 18, 100, "123637");
        userService.createDummy("Sara", "sara", "sara@email.com", "sara", Date.valueOf("1990-11-01"), "Female", "Female", 18, 100, "413377");
        userService.createDummy("Jessica", "jess", "jess@email.com", "jess", Date.valueOf("1980-11-01"), "Female", "No preference", 30, 100, "128337");
        userService.createDummy("Lisa", "lisa", "lisa@email.com", "lisa", Date.valueOf("1970-11-01"), "Female", "No preference", 18, 100, "413397");
        
        tagManager.save("Art");
        tagManager.save("Film");
        tagManager.save("Food");
        tagManager.save("Literature");
        tagManager.save("Music");
        tagManager.save("Photography");
        tagManager.save("Sport");
        tagManager.save("Travel");
        tagManager.save("Video games");
    }

}
