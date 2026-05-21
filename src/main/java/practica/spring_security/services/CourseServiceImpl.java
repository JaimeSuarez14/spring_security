package practica.spring_security.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import practica.spring_security.entities.Course;
import practica.spring_security.entities.User;
import practica.spring_security.repositories.CourseRepository;
import practica.spring_security.repositories.UserRepository;
import practica.spring_security.security.utils.SecurityUtils;

@Service
public class CourseServiceImpl implements CourseService{
	private final CourseRepository courseRepository;
	private final UserRepository userRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Course create(Course course) {
		String username = SecurityUtils.getCurrentUsername();
		User professor = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		course.setProfessor(professor);
		return courseRepository.save(course);
	}
	
}
 