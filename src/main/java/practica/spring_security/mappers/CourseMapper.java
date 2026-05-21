package practica.spring_security.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import practica.spring_security.dto.course.CourseDTO;
import practica.spring_security.dto.course.CourseResponseDTO;
import practica.spring_security.entities.Course;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {

	Course toDomain(CourseDTO courseDTO);
	CourseResponseDTO toResponseDTO(Course course); 
}
