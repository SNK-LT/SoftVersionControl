package com.example.SoftVersionControl.controllers;

import com.example.SoftVersionControl.entities.User;
import com.example.SoftVersionControl.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("adminMainPage")
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("Users", users);
        return "adminMain";
    }
    @PostMapping()
    public String addUser(@RequestParam String login, @RequestParam String password, @RequestParam Integer access, Map<String, Object> model){
        // Проверка значения access
        if (access != null && (access == 0 || access == 1))
        {
            User user = new User(login, password, access);
            userRepo.save(user);

            Iterable<User> users = userRepo.findAll();
            model.put("Users", users);

            return "redirect:/adminMainPage";
        }
        else
        {
            // Ошибка: значение access может быть только 0 или 1
            model.put("LoginError", "Value of access can only be 0 or 1");

            Iterable<User> users = userRepo.findAll();
            model.put("Users", users);

            return "redirect:/adminMainPage";
        }


    }
}
