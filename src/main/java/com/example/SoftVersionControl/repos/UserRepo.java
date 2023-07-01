package com.example.SoftVersionControl.repos;

import com.example.SoftVersionControl.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
