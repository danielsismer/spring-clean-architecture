package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.port.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort userRepositoryPort;

    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user = userRepositoryPort.save(user);
        return userMapper.toResponse(user);
    }
}
