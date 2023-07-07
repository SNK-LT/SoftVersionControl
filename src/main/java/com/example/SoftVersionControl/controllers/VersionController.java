package com.example.SoftVersionControl.controllers;

import com.example.SoftVersionControl.entities.ModuleEntity;
import com.example.SoftVersionControl.entities.VersionEntity;
import com.example.SoftVersionControl.repos.ModuleRepo;
import com.example.SoftVersionControl.repos.VersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VersionController {
    private final VersionRepo versionRepo;
    private final ModuleRepo moduleRepo;

    @Autowired
    public VersionController(VersionRepo versionRepo, ModuleRepo moduleRepo) {
        this.versionRepo = versionRepo;
        this.moduleRepo = moduleRepo;
    }

    @GetMapping("/versions/{moduleId}")
    public String getModuleVersions(@PathVariable Integer moduleId, Model model) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        if (module != null) {
            model.addAttribute("module", module);
            model.addAttribute("versions", versionRepo.findByModule(module));
            return "moduleVersions"; // Название представления для отображения списка версий модуля
        } else {
            return "error"; // Название представления для отображения ошибки или страницы "Модуль не найден"
        }
    }
}
