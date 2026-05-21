package practica.spring_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import practica.spring_security.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	@Query("select u from User u left join u.roles where u.id = ?1")
	Optional<User> getUserWithRoles(Long id);
}