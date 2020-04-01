package pl.pawel.spring_users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pawel.spring_users.model.User;
import pl.pawel.spring_users.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute(new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(err -> System.err.println(err.getDefaultMessage()));
            return "register";
        }
        userService.addUser(user);
        return "redirect:/hello";
    }
}
