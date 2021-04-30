package com.groun24.quarantinder.Services;

import java.sql.Date;
import java.util.List;

import com.groun24.quarantinder.Modal.User;

public interface UserService {
    List<User> get();

    User get(int id);

    User save(User User);

    void update(User User);

    void updatePassword(User user);

    void delete(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhonenumber(String phone);

    void createDummy(String name, String username, String email, String password, Date dob, String gender,
            String genderPreference, int minAge, int maxAge, String number);

}
