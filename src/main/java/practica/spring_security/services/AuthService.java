	package practica.spring_security.services;

import practica.spring_security.entities.User;

public interface AuthService {
	User register(User user);
	String encryptPassword(String password);
	
}
