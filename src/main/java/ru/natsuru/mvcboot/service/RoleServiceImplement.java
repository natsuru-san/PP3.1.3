package ru.natsuru.mvcboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.natsuru.mvcboot.dao.RoleDao;
import ru.natsuru.mvcboot.model.Role;
import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> getAllRoles() {
        return roleDao.pullRoles();
    }

    @Override
    public void setRole(Role role) {
        roleDao.putRole(role);
    }
}
