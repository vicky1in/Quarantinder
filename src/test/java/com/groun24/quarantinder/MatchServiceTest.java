package com.groun24.quarantinder;

import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.MatchManager;

import com.groun24.quarantinder.Services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class MatchServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchManager matchManager;

    @Test
    public void testSaveAndGetMatches() throws Exception {
        List<User> users = new ArrayList<User>();
        assertThat(matchManager).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("Jax", "jax", "jax@gmail.com", "jax", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                35, "6123323");
        users.add(userService.findByUsername("jax"));

        userService.createDummy("Jen", "jen", "jen@gmail.com", "jen", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "6942420");
        users.add(userService.findByUsername("jen"));

        userService.createDummy("Jay", "jay", "jay@gmail.com", "jay", Date.valueOf("2000-11-01"), "Male", "Female",
                18, 34, "6122323");
        users.add(userService.findByUsername("Jay"));

        userService.createDummy("Jane", "jane", "jane@gmail.com", "jane", Date.valueOf("2000-11-01"), "Female",
                "Male", 18, 35, "6942620");
        users.add(userService.findByUsername("jane"));
        assertThat(matchManager.get()).isEmpty();
        
        Match match1 = new Match();
        match1.setAccepted(false);
        match1.setUserA(users.get(0));
        match1.setUserB(users.get(1));
        matchManager.save(match1);

        assertThat(matchManager.get()).isNotNull();
        assertThat(matchManager.get()).isNotEmpty();
        assertEquals(matchManager.get().size(), 1);
        assertEquals(matchManager.get().get(0).getUserA().getId(), users.get(0).getId());
        assertEquals(matchManager.get().get(0).getUserB().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(0).getAccepted(), false);

        Match match2 = new Match();
        match2.setAccepted(true);
        match2.setUserA(users.get(1));
        match2.setUserB(users.get(2));
        matchManager.save(match2);

        assertThat(matchManager.get()).isNotNull();
        assertThat(matchManager.get()).isNotEmpty();
        assertEquals(matchManager.get().size(), 2);
        assertEquals(matchManager.get().get(0).getUserA().getId(), users.get(0).getId());
        assertEquals(matchManager.get().get(0).getUserB().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(0).getAccepted(), false);
        assertEquals(matchManager.get().get(1).getUserA().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(1).getUserB().getId(), users.get(2).getId());
        assertEquals(matchManager.get().get(1).getAccepted(), true);

        Match match3 = new Match();
        match3.setAccepted(true);
        match3.setUserA(users.get(1));
        match3.setUserB(users.get(3));
        matchManager.save(match3);

        assertThat(matchManager.get()).isNotNull();
        assertThat(matchManager.get()).isNotEmpty();
        assertEquals(matchManager.get().size(), 3);
        assertEquals(matchManager.get().get(0).getUserA().getId(), users.get(0).getId());
        assertEquals(matchManager.get().get(0).getUserB().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(0).getAccepted(), false);
        assertEquals(matchManager.get().get(1).getUserA().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(1).getUserB().getId(), users.get(2).getId());
        assertEquals(matchManager.get().get(1).getAccepted(), true);
        assertEquals(matchManager.get().get(2).getUserA().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(2).getUserB().getId(), users.get(3).getId());
        assertEquals(matchManager.get().get(2).getAccepted(), true);

        matchManager.delete(match1.getMatchID());
        matchManager.delete(match2.getMatchID());
        matchManager.delete(match3.getMatchID());
    }

    @Test
    public void testDelete() throws Exception {
        List<User> users = new ArrayList<User>();
        assertThat(matchManager).isNotNull();
        assertThat(userService).isNotNull();
        userService.createDummy("Jax", "jax0", "jax@gmail.com", "jax", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                35, "6123323");
        users.add(userService.findByUsername("jax0"));

        userService.createDummy("Jen", "jen0", "jen@gmail.com", "jen", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "6942420");
        users.add(userService.findByUsername("jen0"));

        Match match = new Match();
        match.setAccepted(false);
        match.setUserA(users.get(0));
        match.setUserB(users.get(1));
        matchManager.save(match);

        assertThat(matchManager.get()).isNotNull();
        assertThat(matchManager.get()).isNotEmpty();
        assertEquals(matchManager.get().size(), 1);
        assertEquals(matchManager.get().get(0).getUserA().getId(), users.get(0).getId());
        assertEquals(matchManager.get().get(0).getUserB().getId(), users.get(1).getId());
        assertEquals(matchManager.get().get(0).getAccepted(), false);

        matchManager.delete(match.getMatchID());

        assertThat(matchManager.get()).isNotNull();
        assertThat(matchManager.get()).isEmpty();
    }

}
