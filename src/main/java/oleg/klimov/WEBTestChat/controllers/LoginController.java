package oleg.klimov.WEBTestChat.controllers;

import oleg.klimov.WEBTestChat.entities.User;
import oleg.klimov.WEBTestChat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (userService.findByUsername(user.getUsername())!=null) {
            model.addAttribute("UserExistsInfo","Пользователь с таким логином уже существует!");
            return "registration";
        }
        else {
            userService.newUser(user);
            model.addAttribute("UserCreateInfo","Пользователь зарегестрерирован!");
            return "login";
        }
    }
}
