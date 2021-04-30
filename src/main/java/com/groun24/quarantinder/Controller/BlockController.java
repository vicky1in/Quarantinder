package com.groun24.quarantinder.Controller;

import java.security.Principal;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.BlockManager;
import com.groun24.quarantinder.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blocks")
public class BlockController {

    @Autowired
    UserService userService;
    
    @Autowired
    BlockManager blockManager;

    @GetMapping
    public String get(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("blocks", this.blockManager.get(user));
        return "blocks";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable int id) {
        blockManager.delete(id);
        return "redirect:/blocks";
    }

}
