package practica.spring_security.services.users;

import practica.spring_security.entities.User;
import practica.spring_security.enums.AssignableRole;

public interface UserService {
	public User updateRole(Long id, AssignableRole role);
}
