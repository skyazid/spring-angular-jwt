package com.example.jwt.entities.security.enums;

import java.util.ArrayList;
import java.util.List;

import static com.example.jwt.entities.security.enums.EnumRole.ADMIN;
import static com.example.jwt.entities.security.enums.EnumRole.USER;

public enum EnumUser {

    ALICE(ADMIN, USER),
    BOB(USER);

    private final String username;
    private final String password;
    private final List<String> roles;

    EnumUser(EnumRole... enumRoles) {
        this.username = this.name().toLowerCase();
        this.password = "password";
        roles = new ArrayList<>();
        for (EnumRole role : enumRoles) {
            roles.add(role.name());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

}
