package com.example.SoftVersionControl.repos;

import com.example.SoftVersionControl.entities.ModuleEntity;
import com.example.SoftVersionControl.entities.BlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepo extends JpaRepository<BlackListEntity, Integer> {
    List<BlackListEntity> findByModule(ModuleEntity module);

}
