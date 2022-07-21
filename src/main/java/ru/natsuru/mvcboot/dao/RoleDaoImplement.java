package ru.natsuru.mvcboot.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.natsuru.mvcboot.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class RoleDaoImplement implements RoleDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Role> pullRoles() {
        return manager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public void putRole(Role role) {
        manager.persist(role);
    }
}
