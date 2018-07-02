package com.example.jwt.service.impl;

import com.example.jwt.dao.AppRoleRepository;
import com.example.jwt.dao.AppUserRepository;
import com.example.jwt.entities.security.AppRole;
import com.example.jwt.entities.security.AppUser;
import com.example.jwt.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AppRoleRepository appRoleRepository;

    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        String passwordHash = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(passwordHash);
        return appUserRepository.save(appUser);
    }

    @Override
    public void saveRole(AppRole appRole) {
        AppRole role = appRoleRepository.findByName(appRole.getName());
        if (role == null) {
            appRoleRepository.save(appRole);
        }
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

}
