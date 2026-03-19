package com.clean.architeture.application.usecase.user;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.port.UserRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserMapper userMapper;
    private final UserRepositoryPort repository;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> find(UserFilter userFilter) {
        return repository.findAll(userFilter)
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
