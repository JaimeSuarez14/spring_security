package practica.spring_security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practica.spring_security.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
