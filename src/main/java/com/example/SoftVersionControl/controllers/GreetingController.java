package com.example.SoftVersionControl.controllers;

import com.example.SoftVersionControl.domain.User;
import com.example.SoftVersionControl.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model){
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("Users", users);
        return "main";
    }

    @PostMapping
    public String addUser(@RequestParam String login, @RequestParam String password, @RequestParam Integer access, Map<String, Object> model){
        User user = new User(login, password, access);
        userRepo.save(user);

        Iterable<User> users = userRepo.findAll();
        model.put("Users", users);

        return "main";
    }
}
