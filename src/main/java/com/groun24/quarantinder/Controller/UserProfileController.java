package com.groun24.quarantinder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.Services.*;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TagManager tagManager;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        return "redirect:/profile/" + user.getId();
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable int id) {
        User user = userService.get(id);
        UserProfile profile = user.getProfile();
        String dob = new SimpleDateFormat("d MMMMM, yyyy").format(user.getDob());
        String lastOnline = new SimpleDateFormat("hh:mm a 'on' d MMMMM, yyyy").format(profile.getLastOnline());

        if (profile.getLocationId() != 0) {
            String city = locationService.get(profile.getLocationId()).getCity();
            model.addAttribute("location", city);
        }

        System.out.println(profile.getPhotoPath());
        
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        model.addAttribute("dob", dob);
        model.addAttribute("lastOnline", lastOnline);
        model.addAttribute("tags", profile.getTags());
        return "profile";
    }

    @GetMapping("/editprofile")
    public String edit(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        UserProfile profile = user.getProfile();

        if (profile.getLocationId() != 0) {
            Location location = locationService.get(profile.getLocationId());
            model.addAttribute("location", location);
        }

        List<Location> locationList = locationService.get();
        model.addAttribute("profile", profile);
        model.addAttribute("profileForm", new UserProfile());
        model.addAttribute("locationList", locationList);
        model.addAttribute("tags", tagManager.get());
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public String edit(@ModelAttribute("profileForm") UserProfile profile, @RequestParam("image") MultipartFile multipartFile, Principal principal) throws IOException {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        UserProfile oldProfile = user.getProfile();
        profile.setProfileId(user.getId());
        profile.setLastOnline(oldProfile.getLastOnline());

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "userphotos/" + profile.getProfileId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            profile.setPhoto(fileName);
        } else {
            profile.setPhoto(oldProfile.getPhoto());
        }

        userProfileService.save(profile);
        return "redirect:/profile";
    }

}