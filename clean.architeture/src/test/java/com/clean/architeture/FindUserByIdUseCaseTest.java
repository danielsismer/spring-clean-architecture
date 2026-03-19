package com.clean.architeture;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.application.usecase.user.FindUserByIdUseCase;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.exception.UserNotFoundException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserByIdUseCaseTest {

    @Mock
    private UserRepositoryPort repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private FindUserByIdUseCase useCase;

    @Test
    @DisplayName("should return user when id exists")
    void shouldReturnUserWhenIdExists() {

        User user = new User("ana@email.com", "123.456.789-01", "(11) 98765-4321");
        UserResponseDTO response = new UserResponseDTO(1L, "ana@email.com", "123.456.789-01", "(11) 98765-4321");

        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(mapper.toResponse(user)).thenReturn(response);

        UserResponseDTO result = useCase.findById(1L);

        assertNotNull(result);
        assertEquals("ana@email.com", result.email());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("should throw UserNotFoundException when id not found")
    void shouldThrowWhenUserNotFound() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> useCase.findById(99L));

        verify(repository, times(1)).findById(99L);
    }


}