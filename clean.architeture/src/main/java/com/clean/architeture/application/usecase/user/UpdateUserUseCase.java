package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort repositoryPort;

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        User user = repositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setCpf(userRequestDTO.cpf());
        user.setEmail(userRequestDTO.email());
        user.setPhone(userRequestDTO.phone());

        user = repositoryPort.save(user);

        return userMapper.toResponse(user);
    }
}
