package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.UserProfile;
import com.groun24.quarantinder.dao.UserProfileDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileServiceImp implements UserProfileService {

    @Autowired
    private UserProfileDAO userProfileDao;

    @Transactional
    @Override
    public List<UserProfile> get() {
        return userProfileDao.get();
    }

    @Transactional
    @Override
    public UserProfile get(int id) {
        return userProfileDao.get(id);
    }

    @Transactional
    @Override
    public void save(UserProfile profile) {
        userProfileDao.save(profile);

    }

    @Transactional
    @Override
    public void delete(int id) {
        userProfileDao.delete(id);

    }

}
