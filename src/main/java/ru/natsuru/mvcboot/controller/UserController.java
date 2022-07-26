package ru.natsuru.mvcboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.natsuru.mvcboot.service.UserService;
import java.security.Principal;
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRootPage(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "/pages/user";
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getOwnPage(Principal principal, Model model) {
        model.addAttribute("owner", userService.getUserObjectByLogin(principal.getName()));
        model.addAttribute("principal", userService.getUserObjectByLogin(principal.getName()));
        return "/pages/user";
    }
}
