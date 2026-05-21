package practica.spring_security.controllers;

import org.springframework.web.bind.annotation.RestController;

import practica.spring_security.dto.ApiResponse;
import practica.spring_security.dto.user.UpdateRoleDTO;
import practica.spring_security.dto.user.UserResponseDto;
import practica.spring_security.entities.User;
import practica.spring_security.mappers.UserMapper;
import practica.spring_security.services.users.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
public class UserController {
  private UserService userService;
  private UserMapper userMapper;
  
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/{id}/role")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateRole(@PathVariable Long id, @RequestBody UpdateRoleDTO dto) {
      User user = userService.updateRole(id, dto.getRole() );
      UserResponseDto userResponseDto = userMapper.toResponseDto(user);

      ApiResponse<UserResponseDto> response = new ApiResponse<UserResponseDto>(
        true, "Usuario actualizado con exito!", 200, userResponseDto
      );

      return ResponseEntity.status(HttpStatus.OK.value()).body(response);
  }
  

}
