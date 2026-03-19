package com.clean.architeture.application.usecase.user;

import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepositoryPort repositoryPort;

    public void delete(Long id) {
        User user = repositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        repositoryPort.delete(id);
    }
}
