package practica.spring_security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practica.spring_security.dto.ApiResponse;
import practica.spring_security.dto.user.UserDto;
import practica.spring_security.dto.user.UserResponseDto;
import practica.spring_security.entities.User;
import practica.spring_security.mappers.UserMapper;
import practica.spring_security.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	private final UserMapper userMapper;

	public AuthController(AuthService authService, UserMapper userMapper) {
		this.authService = authService;
		this.userMapper = userMapper;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponseDto>> create(@RequestBody UserDto userDto){
		User user = userMapper.toDomain(userDto);
		User newUser = authService.register(user);
		UserResponseDto userResponseDto = userMapper.toResponseDto(newUser);
		ApiResponse<UserResponseDto> response = new ApiResponse<UserResponseDto>(true, "Usuario Creado con exito!", 201, userResponseDto );
	
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(response); 
	}
}
