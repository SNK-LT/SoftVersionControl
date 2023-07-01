package com.example.SoftVersionControl.repos;

import com.example.SoftVersionControl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
