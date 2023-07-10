package com.example.SoftVersionControl.repos;

import com.example.SoftVersionControl.entities.ModuleEntity;
import com.example.SoftVersionControl.entities.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepo extends JpaRepository<VersionEntity, Integer> {
    List<VersionEntity> findByModule(ModuleEntity module);

}
