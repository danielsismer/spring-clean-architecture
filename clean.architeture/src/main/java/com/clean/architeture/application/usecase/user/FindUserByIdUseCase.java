package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserByIdUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort repositoryPort;

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        return repositoryPort.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
