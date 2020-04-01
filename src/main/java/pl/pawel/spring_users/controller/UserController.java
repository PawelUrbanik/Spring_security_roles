package pl.pawel.spring_users.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pawel.spring_users.model.Token;
import pl.pawel.spring_users.model.User;
import pl.pawel.spring_users.repository.TokenRepository;
import pl.pawel.spring_users.service.UserService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model){
        List<String> authority= new ArrayList<>();
        String name = principal.getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.forEach(auth -> authority.add(auth.toString().substring(5)));
        if (authority.size() == 1)
        {
            model.addAttribute("authority", authority.get(0));
        }

        model.addAttribute("name", name);
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

    @GetMapping("/token")
    public String confirm(@RequestParam String value, Model model)
    {
        User user = userService.changeUserStatus(value);
        model.addAttribute("name", user.getUsername());
        model.addAttribute("authority", user.getRole().substring(5));
        return "confirmSucces";
    }
}
