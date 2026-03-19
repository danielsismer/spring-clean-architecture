package com.clean.architeture;

import com.clean.architeture.application.mapper.UserMapper;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.exception.EmailAlreadyExistsException;
import com.clean.architeture.domain.port.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveUserUseCaseTest {

    @Mock
    private UserRepositoryPort repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private com.clean.architeture.application.usecase.user.SaveUserUseCase useCase;

    @Test
    @DisplayName("should save user successfully")
    void shouldSaveUser() {

        UserRequestDTO request = new UserRequestDTO("ana@email.com", "123.456.789-01", "(11) 98765-4321");
        User user = new User("ana@email.com", "123.456.789-01", "(11) 98765-4321");
        UserResponseDTO response = new UserResponseDTO(1L, "ana@email.com", "123.456.789-01", "(11) 98765-4321");

        when(repository.existByEmail(request.email())).thenReturn(false);
        when(mapper.toEntity(request)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toResponse(user)).thenReturn(response);

        UserResponseDTO result = useCase.save(request);

        assertNotNull(result);
        assertEquals("ana@email.com", result.email());
        verify(repository, times(1)).save(user);
    }

    @Test
    @DisplayName("should throw EmailJaCadastradoException when email exists")
    void shouldThrowWhenEmailExists() {

        UserRequestDTO request = new UserRequestDTO("ana@email.com", "123.456.789-01", "(11) 98765-4321");

        when(repository.existByEmail(request.email())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class,
                () -> useCase.save(request));

        verify(repository, never()).save(any());
    }
}