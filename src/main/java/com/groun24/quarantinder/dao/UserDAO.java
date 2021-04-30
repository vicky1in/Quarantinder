package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.*;


public interface UserDAO {
    List<User> get();

    User get(int id);
	
    User save(User user);
    
    void delete(int id);

    void update(User user);

}
