package com.groun24.quarantinder.Services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}