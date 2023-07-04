package com.example.SoftVersionControl.repos;

import com.example.SoftVersionControl.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);
}
