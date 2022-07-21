package ru.natsuru.mvcboot.service;

import ru.natsuru.mvcboot.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void setRole(Role role);
}
