package oleg.klimov.WEBTestChat.controllers;

import oleg.klimov.WEBTestChat.entities.Message;
import oleg.klimov.WEBTestChat.entities.User;
import oleg.klimov.WEBTestChat.services.MessageService;
import oleg.klimov.WEBTestChat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users",userService.findAll());
        model.addAttribute("messages",messageService.findLast());
        return "admin";
    }

    @GetMapping("/admin/deleteUser/{user}")
    public String deleteUser(@PathVariable User user) {
        user.setEnabled(false);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/restoreUser/{user}")
    public String restoreUser(@PathVariable User user) {
        user.setEnabled(true);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/editUsername")
    public String editUsername(
            @RequestParam(value = "newUsername") String newUsername,
            @RequestParam(value = "userId") User user) {
        user.setUsername(newUsername);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteMessage/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return "redirect:/admin";
    }

    @PostMapping("admin/editMessage")
    public String editMessage(
            @RequestParam(value = "newMessage") String newMessage,
            @RequestParam(value = "messageId") Message message) {
        message.setText(newMessage);
        messageService.updateMessage(message);
        return "redirect:/admin";
    }
}
