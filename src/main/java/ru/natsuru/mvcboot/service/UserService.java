package ru.natsuru.mvcboot.service;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getListUsers();
    void addUser(String name, String surName, int socialNumber, String mail);
    void addUser(User user);
    void deleteUser(long id);
    void changeUser(User user);
    User getUser(long id);
    User getUserObjectByLogin(String login);
    Set<Role> getRolesFromUser(long id);
}
