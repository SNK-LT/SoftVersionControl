package com.example.SoftVersionControl.controllers;

import com.example.SoftVersionControl.entities.BlackListEntity;
import com.example.SoftVersionControl.entities.ModuleEntity;
import com.example.SoftVersionControl.repos.ModuleRepo;
import com.example.SoftVersionControl.repos.BlackListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlackListController {
    private final BlackListRepo blackListRepo;
    private final ModuleRepo moduleRepo;

    @Autowired
    public BlackListController(BlackListRepo blackListRepo, ModuleRepo moduleRepo) {
        this.blackListRepo = blackListRepo;
        this.moduleRepo = moduleRepo;
    }

    @GetMapping("/versions/{moduleId}")
    public String getModuleVersions(@PathVariable Integer moduleId, Model model) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        if (module != null) {
            model.addAttribute("module", module);
            model.addAttribute("versions", blackListRepo.findByModule(module));
            return "blackList"; // Название представления для отображения списка версий модуля
        } else {
            return "error"; // Название представления для отображения ошибки или страницы "Модуль не найден"
        }
    }

    @PostMapping("/versions/{moduleId}/add")
    public String addModuleVersion(@PathVariable Integer moduleId, @RequestParam String version) {
        ModuleEntity module = moduleRepo.findById(moduleId).orElse(null);
        if (module != null) {
            BlackListEntity newVersion = new BlackListEntity();
            newVersion.setModule(module);
            newVersion.setVersion(version);
            blackListRepo.save(newVersion);
        }
        return "redirect:/versions/" + moduleId;
    }

    @PostMapping("/versions/{moduleId}/delete/{versionId}")
    public String deleteModuleVersion(@PathVariable Integer moduleId, @PathVariable Integer versionId) {
        blackListRepo.deleteById(versionId);
        return "redirect:/versions/" + moduleId;
    }

}
