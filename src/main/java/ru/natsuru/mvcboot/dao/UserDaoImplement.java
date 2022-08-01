package ru.natsuru.mvcboot.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class UserDaoImplement implements UserDao {
    @PersistenceContext
    private EntityManager manager;

    public UserDaoImplement() {}

    @Override
    public List<User> pullListUsers() {
        return manager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void putUser(User user) {
        user.setPass(getEncodedPassword(user.getPassword()));
        manager.merge(user);
    }

    @Override
    public void removeUser(long id) {
        Query query = manager.createQuery("DELETE FROM User User WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
    @Override
    public void updateUser(User user) {
        if (isExistUserById(user.getId())) {
            user.setPass(getEncodedPassword(user.getPassword()));
            manager.merge(user);
        }
    }

    @Override
    public User pullUser(long id) {
        User user = manager.find(User.class, id);
        if (user == null) {
            user = new User("Not defined", "Not defined", -1, "null@mail.null");
            user.setId(-1L);
        }
        return user;
    }

    @Override
    public Set<Role> pullRolesFromUser(long id) {
        return pullUser(id).getRoles();
    }

    @Override
    public User pullUserByName(String login) {
        TypedQuery<User> query = manager.createQuery("FROM User user WHERE user.name=:name", User.class);
        query.setParameter("name", login);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    private boolean isExistUserById(long id) {
        return manager.find(User.class, id) != null;
    }
    private String getEncodedPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
