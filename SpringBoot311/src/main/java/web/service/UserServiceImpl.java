package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.Model.Role;
import web.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.repository.RoleRep;
import web.repository.UserRep;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

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

    public void delete(int id) {
        userDao.delete(id);
    }
}
