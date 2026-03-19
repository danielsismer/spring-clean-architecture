package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindByCpfUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort repositoryPort;

    public UserResponseDTO find(String cpf) {
        return repositoryPort.findByCpf(cpf)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

}
