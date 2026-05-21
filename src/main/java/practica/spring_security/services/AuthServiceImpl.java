package practica.spring_security.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practica.spring_security.entities.Role;
import practica.spring_security.entities.User;
import practica.spring_security.repositories.RoleRepository;
import practica.spring_security.repositories.UserRepository;
@Service
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	private  final  PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
 	}


	@Override
	@Transactional
	public User register(User user) {
		String passwordHash = encryptPassword(user.getPasswordHash());
		user.setPasswordHash(passwordHash);
		
		Set<Role> roles = new HashSet<Role>();
		Optional<Role> optionalRole = roleRepository.findByName("ROLE_STUDENT");
		optionalRole.ifPresent(roles::add);
		
		user.setRoles(roles);
		
		return userRepository.save(user);
	}


	@Override
	public String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
