package com.groun24.quarantinder.Controller;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    UserService userService;
    @GetMapping("/posts")
    public String blog(Model model, Principal principal){
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user);
        return "blogs";
    }

    @GetMapping("/create")
    public String create(Model model, Principal principal){
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/visit/{id}")
    public String visit(Model model, Principal principal, @PathVariable Integer id){
        String name = principal.getName();
        User user = userService.findByUsername(name);
        User visitedUser = userService.get(id);
        model.addAttribute("user", user);
        model.addAttribute("visitedUser", visitedUser);
        return "visit";
    }
}
