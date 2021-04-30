package com.groun24.quarantinder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.sql.Timestamp;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.Services.*;
import com.groun24.quarantinder.config.AccountDetailsValidator;
import com.groun24.quarantinder.config.PasswordValidator;
import com.groun24.quarantinder.config.UserValidator;

@Controller
//@RequestMapping("/api")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserProfileService profileService;


    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private LocationService locationService;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PasswordValidator passwordValidator;
    
    @Autowired
    private AccountDetailsValidator accountDetailsValidator;



    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    
    @GetMapping( "/welcome")
    public String welcome(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        UserProfile profile = user.getProfile();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        profile.setLastOnline(timestamp);
        userProfileService.save(profile);
        model.addAttribute("user", user);
        return "welcome";
    }
    
    @PostMapping("/registration")
    public String registration( @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

    	userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        // insert new profile when creating new user
        UserProfile newProfile = new UserProfile();
        newProfile.setLastOnline(new Timestamp(System.currentTimeMillis()));
        newProfile.setBio("It's empty in here...");
        Location sydney = locationService.findByCity("Sydney");
        newProfile.setLocationId(sydney.getLocationID());
        userForm.setProfile(newProfile);

        userService.save(userForm);
        userProfileService.save(newProfile);
        
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
    @GetMapping({"/","/login"})
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");        
        return "login";
    }
    

    @GetMapping("/user")
    public List<User> get() {
        return userService.get();
    }
    
    @PostMapping("/user")
    public User save(@RequestBody User user) {
    	userService.save(user);
     return user;
    }
    
    @GetMapping("/user/{id}")
    public User get(@PathVariable int id) {
     return userService.get(id);
    }
    
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {
     
    	userService.delete(id);
     
     return "User removed with id "+id;
     
    }
    
    @PutMapping("/user")
    public User update(@RequestBody User user) {
    	userService.save(user);
     return user;
    }
    
    @GetMapping("/edit/updatePassword")
    public String editPassword (Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user );
    	return "updatePassword";
    } 
    
    @PostMapping("/edit/updatePassword")
    public String editPassword (@ModelAttribute("user") User user, BindingResult bindingResult) {
    	
        passwordValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
        	String error = bindingResult.getAllErrors().toString();
        	System.out.printf(error);
            return "updatePassword";
        }
 	
        UserProfile profile = profileService.get(user.getId());
        user.setProfile(profile);
    	userService.updatePassword(user);

    	return "redirect:/editprofile";
    }    
    
    @GetMapping("/edit/updateDetails")
    public String editDetails (Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user );
    	return "updateDetails";
    } 
    @PostMapping("/edit/updateDetails")
    public String editDetails (@ModelAttribute("user") User user, BindingResult bindingResult) {

    	accountDetailsValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
        	String error = bindingResult.getAllErrors().toString();
        	System.out.printf(error);
            return "updateDetails";
        }


        UserProfile profile = profileService.get(user.getId());
        user.setProfile(profile);
    	userService.update(user);

    	return "redirect:/profile";
    }    

    @GetMapping("/edit/deleteConfirm")
    public String deleteConfirm() {
        return "deleteConfirm";
    }

    @PostMapping("/edit/deleteConfirm")
    public String deleteConfirm(Principal principal) {
        SecurityContextHolder.getContext().setAuthentication(null);
        String name = principal.getName();
        User user = userService.findByUsername(name);
        userService.delete(user.getId());
        return "login";
    }
    
}