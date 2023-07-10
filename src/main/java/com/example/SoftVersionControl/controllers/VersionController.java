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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/versions/{moduleId}/add")
    public String addModuleVersion(@PathVariable Integer moduleId, @RequestParam String version, Model model) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        if (module != null) {
            VersionEntity newVersion = new VersionEntity();
            newVersion.setModule(module);
            newVersion.setVersion(version);
            versionRepo.save(newVersion);
        }
        return "redirect:/versions/{moduleId}"; // Перенаправление на страницу с версиями модуля
    }

    @PostMapping("/versions/{moduleId}/make-actual/{versionId}")
    public String makeVersionActual(@PathVariable Integer moduleId, @PathVariable Integer versionId) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        VersionEntity version = versionRepo.findById(versionId).orElse(null);
        if (module != null && version != null) {
            module.setActual_version(version.getVersion());
            moduleRepo.save(module);
        }
        return "redirect:/versions/" + moduleId;
    }

    @PostMapping("/versions/{moduleId}/make-minimal/{versionId}")
    public String makeVersionMinimal(@PathVariable Integer moduleId, @PathVariable Integer versionId) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        VersionEntity version = versionRepo.findById(versionId).orElse(null);
        if (module != null && version != null) {
            module.setMin_version(version.getVersion());
            moduleRepo.save(module);
        }
        return "redirect:/versions/" + moduleId;
    }

    @PostMapping("/versions/{moduleId}/delete/{versionId}")
    public String deleteVersion(@PathVariable Integer moduleId, @PathVariable Integer versionId) {
        versionRepo.deleteById(versionId);
        return "redirect:/versions/" + moduleId;
    }
}
