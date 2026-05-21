package practica.spring_security.services.users;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practica.spring_security.entities.Role;
import practica.spring_security.entities.User;
import practica.spring_security.enums.AssignableRole;
import practica.spring_security.repositories.RoleRepository;
import practica.spring_security.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}



	@Override
	@Transactional
	public User updateRole(Long id, AssignableRole role){
		User userWithRoles = userRepository.getUserWithRoles(id)
			.orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado"));

		Optional<Role> optionalRole = roleRepository.findByName("ROLE_" + AssignableRole.TEACHER.toString());
		optionalRole.ifPresent(userWithRoles::addRole);

		return userWithRoles;
	}
}
