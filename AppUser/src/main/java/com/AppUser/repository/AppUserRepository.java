package com.AppUser.repository;

import com.AppUser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    public Optional<AppUser>findByUsername(String username);
}
