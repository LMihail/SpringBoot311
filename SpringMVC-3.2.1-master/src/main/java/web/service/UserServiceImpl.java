package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.UserDaoImpl;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDao;

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> listUser() {
        return userDao.listUser();
    }

    public User loadUserByUsername(String name) {
        return userDao.loadUserByUsername(name);
    }
}
