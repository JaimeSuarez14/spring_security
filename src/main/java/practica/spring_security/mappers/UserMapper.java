package practica.spring_security.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import practica.spring_security.dto.user.UserDto;
import practica.spring_security.dto.user.UserResponseDto;
import practica.spring_security.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "passwordHash" , source ="password")
	User toDomain(UserDto dto);
	UserResponseDto toResponseDto(User user);
}
