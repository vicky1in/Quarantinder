package com.groun24.quarantinder.Services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.dao.RoleDAO;
import com.groun24.quarantinder.dao.UserDAO;
import com.groun24.quarantinder.dao.UserRegistrationDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRegistrationDAO UserRegistration;
    @Autowired
    private RoleDAO RoleDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO UserDao;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Transactional
    @Override
    public List<User> get() {
        return UserDao.get();
    }

    @Transactional
    @Override
    public User get(int id) {
        return UserDao.get(id);
    }

    @Transactional
    @Override
    public User save(User User) {
        User.setPassword(bCryptPasswordEncoder.encode(User.getPassword()));
        User.setRoles(new HashSet<>(RoleDAO.findAll()));
        return UserDao.save(User);
    }

    @Transactional
    @Override
    public void updatePassword(User User) {

        if (!bCryptPasswordEncoder.matches(User.getPassword(),
                userService.findByUsername(User.getUsername()).getPassword())) {
            User.setPassword(bCryptPasswordEncoder.encode(User.getPassword()));
        }
        User.setProfile(User.getProfile());
        UserDao.update(User);
    }

    @Transactional
    @Override
    public void update(User User) {

        User.setProfile(User.getProfile());
        UserDao.update(User);
    }

    @Transactional
    @Override
    public void delete(int id) {
        UserDao.delete(id);

    }

    @Override
    public User findByUsername(String username) {
        return UserRegistration.findByUsername(username);
    }

    @Transactional
    @Override
    public void createDummy(String name, String username, String email, String password, Date dob, String gender,
            String genderPreference, int minAge, int maxAge, String number) {

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setDob(dob);
        user.setGender(gender);
        user.setPhonenumber(number);

        UserProfile profile = new UserProfile();
        profile.setLastOnline(new Timestamp(System.currentTimeMillis()));
        profile.setBio("It's empty in here...");
        Location sydney = locationService.findByCity("Sydney");
        profile.setLocationId(sydney.getLocationID());
        profile.setGenderPreference(genderPreference);
        profile.setMinAgePreference(minAge);
        profile.setMaxAgePreference(maxAge);
        user.setProfile(profile);

        UserDao.save(user);
        userProfileService.save(profile);
    }

    @Override
    public User findByEmail(String email) {
        return UserRegistration.findByEmail(email);
    }

    @Override
    public User findByPhonenumber(String phone) {
        return UserRegistration.findByPhonenumber(phone);
    }

}
