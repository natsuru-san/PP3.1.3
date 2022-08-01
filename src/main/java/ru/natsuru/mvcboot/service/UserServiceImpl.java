package ru.natsuru.mvcboot.service;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import ru.natsuru.mvcboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getListUsers() {
        return userDao.pullListUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.putUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    public void changeUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDao.pullUser(id);
    }

    @Override
    public User getUserObjectByLogin(String login) {
        return userDao.pullUserByName(login);
    }

    @Override
    public Set<Role> getRolesFromUser(long id) {
        return userDao.pullRolesFromUser(id);
    }
}
