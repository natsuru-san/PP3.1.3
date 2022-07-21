package ru.natsuru.mvcboot.dao;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> pullListUsers();
    void putUser(String name, String surName, int socialNumber, String mail);
    void putUser(User user);
    void removeUser(long id);
    void updateUser(User user);
    User pullUser(long id);
    Set<Role> pullRolesFromUser(long id);
    User pullUserByName(String login);
}
