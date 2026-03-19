package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.exception.DomainException;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FindByEmailUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort userRepositoryPort;

    @Transactional(readOnly = true)
    public UserResponseDTO find(String email) {
        return userRepositoryPort.findByEmail(email)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
