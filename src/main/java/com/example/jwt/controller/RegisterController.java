package com.example.jwt.controller;

import com.example.jwt.entities.security.AppUser;
import com.example.jwt.entities.security.enums.EnumRole;
import com.example.jwt.service.AccountService;
import com.example.jwt.view.RegisterForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.jwt.controller.ApplicationRoutes.REGISTER;

@RestController
public class RegisterController {

    private final AccountService accountService;

    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(REGISTER)
    public AppUser register(@RequestBody RegisterForm registerForm) {
        if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
            throw new RuntimeException("You must confirm your password");
        }
        if (accountService.findUserByUsername(registerForm.getUsername()) != null) {
            throw new RuntimeException("This user already exists");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
        appUser = this.accountService.saveUser(appUser);
        accountService.addRoleToUser(registerForm.getUsername(), EnumRole.USER.name());
        return appUser;
    }

}
