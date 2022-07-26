package ru.natsuru.mvcboot.controller;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.natsuru.mvcboot.service.RoleService;
import ru.natsuru.mvcboot.service.UserService;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public String getAllUsers(Principal principal, Model model) {
        model.addAttribute("listUsers", userService.getListUsers());
        model.addAttribute("principal", userService.getUserObjectByLogin(principal.getName()));
        return "/pages/users.html";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String redirectToAdminListOfUsers() {
        return "redirect:/admin/list";
    }

    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String addNewUser(Principal principal, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("principal", userService.getUserObjectByLogin(principal.getName()));
        return "/pages/new.html";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String creatingNewUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    //Цей метод контролера треба використовувати через кнопку в шаблоні html, інакше помилка #405
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/change", method = RequestMethod.PATCH)
    public String changeUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.changeUser(user);
        return "redirect:/admin";
    }
}
