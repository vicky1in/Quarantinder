package com.groun24.quarantinder;

import com.groun24.quarantinder.Controller.MatchController;
import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.MatchManager;

import com.groun24.quarantinder.Services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MatchControllerTest {
    @Autowired
    private MatchController matchController;

    @Autowired
    private UserService userService;

    @Autowired
	private MatchManager matchManager;
	
    @Test
    public void testGet() throws Exception {

		List<User> users = new ArrayList<User>();
        userService.createDummy("Jax", "jax1", "jax@gmail.com", "jax", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                35, "6123323");
        users.add(userService.findByUsername("jax1"));
        userService.createDummy("Jen", "jen1", "jen@gmail.com", "jen", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "6942420");
        users.add(userService.findByUsername("jen1"));
        assertThat(matchController).isNotNull();
		assertThat(userService).isNotNull();
		assertEquals(matchManager.get().size(), 0);
		Principal mockPrincipal = mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn(users.get(0).getUsername());
		Model mod = new ConcurrentModel();
		
		String view = matchController.get(mod, mockPrincipal);
		assertEquals(view, "matches");
		assertThat(mod.getAttribute("acceptedMatches")).isNotNull();
		assertEquals( ((List<Match>) mod.getAttribute("acceptedMatches")).size(), 0);
		assertThat(mod.getAttribute("sentMatches")).isNotNull();
		assertEquals( ((List<Match>) mod.getAttribute("sentMatches")).size(), 0);
		assertThat(mod.getAttribute("receivedMatches")).isNotNull();
		assertEquals( ((List<Match>) mod.getAttribute("receivedMatches")).size(), 0);

		Match match = new Match();
		match.setAccepted(true);
		match.setUserA(users.get(0));
		match.setUserB(users.get(1));
		matchManager.save(match);
		assertThat(matchManager.get(match.getMatchID())).isNotNull();

		view = matchController.get(mod, mockPrincipal);
		assertEquals(view, "matches");
		assertThat(mod.getAttribute("acceptedMatches")).isNotNull();
		assertEquals( ((List<Match>) mod.getAttribute("acceptedMatches")).size(), 1);
		assertEquals( ((List<Match>) mod.getAttribute("acceptedMatches")).get(0).getMatchID(), match.getMatchID());
	}

	@Test
	public void testCancel() throws Exception {

        List<User> users = new ArrayList<User>();
        userService.createDummy("Jax", "jax2", "jax@gmail.com", "jax", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                35, "6123323");
        users.add(userService.findByUsername("jax2"));
        userService.createDummy("Jen", "jen2", "jen@gmail.com", "jen", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "6942420");
        users.add(userService.findByUsername("jen2"));
        assertThat(matchController).isNotNull();
		assertThat(userService).isNotNull();
		Match match = new Match();
		match.setAccepted(false);
		match.setUserA(users.get(0));
		match.setUserB(users.get(1));
		matchManager.save(match);
		assertEquals(matchManager.get(users.get(0), users.get(1)).getMatchID(), match.getMatchID());
		Principal mockPrincipal = mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn(users.get(1).getUsername());
		matchController.cancel(match.getMatchID());
		assertEquals(matchManager.get(match.getMatchID()), null);
	}

	@Test
	public void testAccept() throws Exception {

        List<User> users = new ArrayList<User>();
        userService.createDummy("Jax", "jax3", "jax@gmail.com", "jax", Date.valueOf("2000-11-01"), "Male", "Female", 18,
                35, "6123323");
        users.add(userService.findByUsername("jax3"));
        userService.createDummy("Jen", "jen3", "jen@gmail.com", "jen", Date.valueOf("2000-11-01"), "Female", "Male", 18,
                35, "6942420");
        users.add(userService.findByUsername("jen3"));
        assertThat(matchController).isNotNull();
		assertThat(userService).isNotNull();
		Match match = new Match();
		match.setAccepted(false);
		match.setUserA(users.get(1));
		match.setUserB(users.get(0));
		matchManager.save(match);
		assertEquals(matchManager.get(users.get(1), users.get(0)).getMatchID(), match.getMatchID());
		Principal mockPrincipal = mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn(users.get(0).getUsername());
		matchController.accept(match.getMatchID());
		assertEquals(matchManager.get(match.getMatchID()).getMatchID(), match.getMatchID());
		assertEquals(matchManager.get(match.getMatchID()).getAccepted(), true);
	}
}