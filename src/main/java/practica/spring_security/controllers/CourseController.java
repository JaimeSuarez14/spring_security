package practica.spring_security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practica.spring_security.dto.ApiResponse;
import practica.spring_security.dto.course.CourseDTO;
import practica.spring_security.dto.course.CourseResponseDTO;
import practica.spring_security.entities.Course;
import practica.spring_security.mappers.CourseMapper;
import practica.spring_security.services.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	private CourseService courseService;
	private CourseMapper courseMapper;
	
	public CourseController(CourseService courseService, CourseMapper courseMapper) {
		this.courseService = courseService;
		this.courseMapper = courseMapper;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<CourseResponseDTO>> create(@RequestBody CourseDTO dto)
	{
		Course course = courseMapper.toDomain(dto);
		Course newCourse = courseService.create(course);
		CourseResponseDTO courseResponseDTO = courseMapper.toResponseDTO(newCourse);
		
		ApiResponse<CourseResponseDTO> response = new ApiResponse<CourseResponseDTO>(
				true, "Curso creado con exito!", 201, courseResponseDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
	}
}
