package com.example.jwt.service;

import com.example.jwt.entities.security.AppRole;
import com.example.jwt.entities.security.AppUser;

public interface AccountService {

    AppUser saveUser(AppUser appUser);

    void saveRole(AppRole appRole);

    void addRoleToUser(String username, String roleName);

    AppUser findUserByUsername(String Username);

}
