package web.repository;

import org.springframework.data.repository.CrudRepository;
import web.Model.User;


public interface UserRep extends CrudRepository<User, Integer> {
}
