package com.example.jwt.service.impl;

import com.example.jwt.dao.TaskRepository;
import com.example.jwt.entities.Task;
import com.example.jwt.entities.security.AppRole;
import com.example.jwt.entities.security.AppUser;
import com.example.jwt.entities.security.enums.EnumUser;
import com.example.jwt.service.AccountService;
import com.example.jwt.service.StarterService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StarterServiceImpl implements StarterService {

    private final AccountService accountService;

    private final TaskRepository taskRepository;

    public StarterServiceImpl(AccountService accountService, TaskRepository taskRepository) {
        this.accountService = accountService;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        this.addUsers();
        this.addTasks();
    }

    public void addUsers() {
        Arrays.asList(EnumUser.values()).forEach(enumUser -> {
            accountService.saveUser(new AppUser(null, enumUser.getUsername(), enumUser.getPassword(), null));
            enumUser.getRoles().forEach(role -> {
                accountService.saveRole(new AppRole(null, role));
                accountService.addRoleToUser(enumUser.getUsername(), role);
            });
        });
    }

    public void addTasks() {
        taskRepository.saveAll(
                IntStream.range(1, 111)
                        .mapToObj(i -> {
                            Task task = new Task();
                            task.setName("name " + i);
                            return task;
                        })
                        .collect(Collectors.toList())
        );
    }

}
