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
public class LoginController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("Users", users);
        return "login";
    }

    @PostMapping("/logInSystem")
    public  String logInSystem(@RequestParam("inLogin") String inLogin, @RequestParam("inPassword") String inPassword, Map<String, Object> model){

        User loggedUser = userRepo.findByLogin(inLogin);
        if(loggedUser != null)
        {
            if(loggedUser.getPassword().equals(inPassword))
            {
                return "redirect:/adminMainPage";
            }
            else
            {
                model.put("LoginError", "Wrong Password!");
                return "login";
            }
        }
        else
        {
            model.put("LoginError", "Wrong Login!");
            return "login";
        }
    }


}
