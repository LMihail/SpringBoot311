package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.Model.Role;
import web.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.repository.RoleRep;
import web.repository.UserRep;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

//    @PersistenceContext
//    private EntityManager em;
    @Autowired
    UserRep rep;
    @Autowired
    RoleRep roleRep;
//    @Autowired
//    PasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
        Set<Role> roles = user.getRoles();
        if(roles == null) {
            roles = new HashSet<>();
            roles.add(roleRep.findById(1L).get());
            user.setRoles(roles);
        }
        rep.save(user);
    }

    public void removeUser(int id) {
        if (rep.findById(id).isPresent()) {
            rep.deleteById(id);
        }
    }

    public User getUserById(int id) {
        Optional<User> userFromDb = rep.findById(id);
        return userFromDb.orElse(new User());
    }

    public List<User> listUser() {
        return (List<User>) rep.findAll();
    }

    public User loadUserByUsername(String name) {
        return rep.findUserByName(name);
    }

}
