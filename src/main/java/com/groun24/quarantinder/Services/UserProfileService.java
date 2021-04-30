package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.UserProfile;

public interface UserProfileService {
    List<UserProfile> get();
 
    UserProfile get(int id);
    
    void save(UserProfile profile);
    
    void delete(int id);
}
