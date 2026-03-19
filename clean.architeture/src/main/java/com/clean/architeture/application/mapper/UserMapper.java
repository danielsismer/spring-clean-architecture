package com.clean.architeture.application.mapper;

import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getCpf(),
                user.getPhone()
        );
    }

    public User toEntity(UserRequestDTO userRequestDTO){
        return new User(
                userRequestDTO.email(),
                userRequestDTO.cpf(),
                userRequestDTO.phone()
        );
    }

}
