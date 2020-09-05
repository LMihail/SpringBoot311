package web.repository;

import org.springframework.data.repository.CrudRepository;
import web.Model.Role;

public interface RoleRep extends CrudRepository<Role, Long> {
}
