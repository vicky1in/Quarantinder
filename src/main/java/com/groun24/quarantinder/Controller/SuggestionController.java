package com.groun24.quarantinder.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Map.Entry;

import com.groun24.quarantinder.Modal.Block;
import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.Services.BlockManager;
import com.groun24.quarantinder.Services.LocationService;
import com.groun24.quarantinder.Services.MatchManager;
import com.groun24.quarantinder.Services.MatchSuggester;
import com.groun24.quarantinder.Services.ReportManager;
import com.groun24.quarantinder.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/suggestions")
public class SuggestionController {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchManager matchManager;

    @Autowired
    private MatchSuggester matchSuggester;

    @Autowired
    BlockManager blockManager;

    @Autowired
    ReportManager reportManager;

    @Autowired
    LocationService locationService;

    @GetMapping
    public String get(@RequestParam(required = false) String sLocation, Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        Location location = null;
        List<Entry<UserProfile, Integer>> suggestions;
        if (sLocation != null) {
                if (sLocation.equals("nearest")) {
                    suggestions = this.matchSuggester.getSuggestions(user, location, true);
                }
                else {
                    location = locationService.findByCity(sLocation);
                    suggestions = this.matchSuggester.getSuggestions(user, location, false);
                }
        }
        else {
            suggestions = this.matchSuggester.getSuggestions(user, location, false);
        }
        model.addAttribute("suggestions", suggestions);
        model.addAttribute("locations", locationService.get());
        return "suggestions";
    }

    @PostMapping("/match/{id}")
    public String request(@PathVariable int id, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);

        Match match = new Match();
        match.setUserA(user);
        match.setUserB(userService.get(id));
        match.setAccepted(false);
        matchManager.save(match);

        return "redirect:/suggestions";
    }

    @PostMapping("/block/{id}")
    public String block(@PathVariable int id, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        User otherUser = userService.get(id);

        Block block = new Block();
        block.setBlocker(user);
        block.setBlockee(otherUser);
        blockManager.save(block);

        Match match = matchManager.get(user, otherUser);
        if (match != null) {
            matchManager.delete(match.getMatchID());
        }
        return "redirect:/suggestions";
    }

}
