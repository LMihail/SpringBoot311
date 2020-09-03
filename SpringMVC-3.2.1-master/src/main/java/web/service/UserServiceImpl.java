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

//    @Autowired
//    UserRep rep;
//    @Autowired
//    RoleRep roleRep;

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

//    @Transactional
//    public void saveUser(User user) {
//        Set<Role> roles = user.getRoles();
//        if (roles == null) {
//            roles = new HashSet<>();
//            roles.add(roleRep.findById(1L).get());
//            user.setRoles(roles);
//        }
//        rep.save(user);
//    }
//
//    @Transactional
//    public void removeUser(int id) {
//        if (rep.findById(id).isPresent()) {
//            rep.deleteById(id);
//        }
//    }
//
//    @Transactional
//    public User getUserById(int id) {
//        Optional<User> userFromDb = rep.findById(id);
//        return userFromDb.orElse(new User());
//    }
//
//    @Transactional
//    public List<User> listUser() {
//        return (List<User>) rep.findAll();
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return listUser().stream()
//                .filter(a -> username.equals(a.getName()))
//                .findFirst()
//                .orElse(null);
//    }

}
