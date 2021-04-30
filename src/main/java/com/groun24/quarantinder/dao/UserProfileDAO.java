package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.*;

public interface UserProfileDAO {
    List<UserProfile> get();

    UserProfile get(int id);

    void save(UserProfile profile);

    void delete(int id);
}
