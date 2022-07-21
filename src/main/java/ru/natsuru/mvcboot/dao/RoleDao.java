package ru.natsuru.mvcboot.dao;

import ru.natsuru.mvcboot.model.Role;
import java.util.List;

public interface RoleDao {
    List<Role> pullRoles();
    void putRole(Role role);
}
