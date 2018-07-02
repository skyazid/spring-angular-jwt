package com.example.jwt.dao;

import com.example.jwt.entities.security.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByName(String name);

}
