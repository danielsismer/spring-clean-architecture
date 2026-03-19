package com.clean.architeture.controller;

import com.clean.architeture.application.usecase.user.*;
import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import com.clean.architeture.domain.entity.User;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindByEmailUseCase findByEmailUseCase;
    private final FindByCpfUseCase findByCpfUseCase;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveUserUseCase.save(userRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> find(@ModelAttribute UserFilter userFilter){
        return ResponseEntity.status(HttpStatus.OK)
                .body(findUserUseCase.find(userFilter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(findUserByIdUseCase.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO userRequestDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateUserUseCase.update(id, userRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteById(@PathVariable Long id){
        deleteUserUseCase.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    // search by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(findByEmailUseCase.find(email));
    }

    //search by cpf
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponseDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.OK)
                .body(findByCpfUseCase.find(cpf));
    }

}
