package practica.spring_security.dto.course;

import java.time.LocalDateTime;

import practica.spring_security.entities.User;

public class CourseResponseDTO {
	private Long id;
	private String title;
	private String description;
	private LocalDateTime published;
	private User professor;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getPublished() {
		return published;
	}
	public void setPublished(LocalDateTime published) {
		this.published = published;
	}
	public User getProfessor() {
		return professor;
	}
	public void setProfessor(User professor) {
		this.professor = professor;
	}
	
	
}
