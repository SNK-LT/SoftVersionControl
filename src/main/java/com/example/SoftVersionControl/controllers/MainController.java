package com.example.SoftVersionControl.controllers;

import com.example.SoftVersionControl.entities.ModuleEntity;
import com.example.SoftVersionControl.entities.UserEntity;
import com.example.SoftVersionControl.repos.ModuleRepo;
import com.example.SoftVersionControl.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModuleRepo moduleRepo;

    @GetMapping("mainPage")
    public String main(Model model) {
        Iterable<UserEntity> users = userRepo.findAll();
        Iterable<ModuleEntity> modules = moduleRepo.findAll();

        model.addAttribute("Users", users);
        model.addAttribute("ModuleEntity", modules);

        return "mainPage";
    }

    @PostMapping()
    public String addUser(@RequestParam String login, @RequestParam String password, @RequestParam Integer access, Map<String, Object> model) {
        // Проверка значения access
        if (access != null && (access == 0 || access == 1)) {
            UserEntity user = new UserEntity(login, password, access);
            userRepo.save(user);

            Iterable<UserEntity> users = userRepo.findAll();
            model.put("Users", users);

            return "redirect:/mainPage";
        } else {
            // Ошибка: значение access может быть только 0 или 1
            model.put("LoginError", "Value of access can only be 0 or 1");

            Iterable<UserEntity> users = userRepo.findAll();
            model.put("Users", users);

            return "redirect:/mainPage";
        }
    }
}
