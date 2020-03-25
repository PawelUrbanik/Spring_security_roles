package pl.pawel.spring_users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }


    @GetMapping("/for-user")
    public String forUser(){
        return "for-user";
    }

    @GetMapping("/for-admin")
    public String forAdmin(){
        return "for-admin";
    }
}
