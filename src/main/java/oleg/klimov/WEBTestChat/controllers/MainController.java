package oleg.klimov.WEBTestChat.controllers;

import oleg.klimov.WEBTestChat.entities.User;
import oleg.klimov.WEBTestChat.services.MessageService;
import oleg.klimov.WEBTestChat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String main() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String homePage(Model model) {
        model.addAttribute("users",userService.findAll());
        model.addAttribute("messages",messageService.findLast());
        return "index";
    }

    @PostMapping("/addMessage")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "text") String text,
            @RequestParam(value = "userId") Long userId) {
        messageService.newMessage(user, text, userService.findById(userId));
        return "redirect:/index";
    }
}
