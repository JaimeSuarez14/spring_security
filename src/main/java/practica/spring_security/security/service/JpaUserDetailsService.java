package practica.spring_security.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practica.spring_security.entities.User;
import practica.spring_security.repositories.UserRepository;
@Service
public class JpaUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	JpaUserDetailsService(UserRepository userRepository){
		this.userRepository =  userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser  = userRepository.findByUsername(username);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException( String.format("El usuario %s no existe en el sistema", username));
		}
		User user = optionalUser.orElseThrow();
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPasswordHash(),
				true,
				true,
				true,
				true,
				authorities
				
				);
	}

}
