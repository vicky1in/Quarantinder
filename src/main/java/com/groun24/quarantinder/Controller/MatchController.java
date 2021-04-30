package com.groun24.quarantinder.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.groun24.quarantinder.Modal.Block;
import com.groun24.quarantinder.Modal.Match;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.BlockManager;
import com.groun24.quarantinder.Services.MatchManager;
import com.groun24.quarantinder.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matches")
public class MatchController {

  @Autowired
  private UserService userService;

  @Autowired
  private MatchManager matchManager;

  @Autowired
  BlockManager blockManager;

  @GetMapping
  public String get(Model model, Principal principal) {
    String name = principal.getName();
    User user = userService.findByUsername(name);
    List<Match> matches = this.matchManager.get(user);
    List<Match> acceptedMatches = new ArrayList<Match>();
    List<Match> sentMatches = new ArrayList<Match>();
    List<Match> receivedMatches = new ArrayList<Match>();
    for (Match match : matches) {
      if (match.getAccepted()) {
        acceptedMatches.add(match);
      }
      else if (match.getUserA().equals(user)) {
        sentMatches.add(match);
      }
      else if (match.getUserB().equals(user)) {
        receivedMatches.add(match);
      }
    }
    model.addAttribute("acceptedMatches", acceptedMatches);
    model.addAttribute("sentMatches", sentMatches);
    model.addAttribute("receivedMatches", receivedMatches);
    return "matches";
  }

  @PostMapping("/cancel/{id}")
  public String cancel(@PathVariable int id) {
    matchManager.delete(id);
    return "redirect:/matches";
  }

  @PostMapping("/accept/{id}")
  public String accept(@PathVariable int id) {
    Match match = matchManager.get(id);
    match.setAccepted(true);
    matchManager.save(match);
    return "redirect:/matches";
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
        return "redirect:/matches";
    }
}