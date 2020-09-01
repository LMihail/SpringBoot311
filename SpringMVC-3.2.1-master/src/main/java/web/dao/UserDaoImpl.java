package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Role;
import web.Model.User;
import web.repository.RoleRep;
import web.repository.UserRep;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDaoImpl implements UserDao{

    @Autowired
    UserRep rep;
    @Autowired
    RoleRep roleRep;

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
