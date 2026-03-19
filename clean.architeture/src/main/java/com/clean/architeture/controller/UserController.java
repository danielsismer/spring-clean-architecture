package com.clean.architeture.controller;

import com.clean.architeture.application.usecase.user.*;
import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.dto.request.UserRequestDTO;
import com.clean.architeture.domain.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for user management")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindByEmailUseCase findByEmailUseCase;
    private final FindByCpfUseCase findByCpfUseCase;

    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid fields"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(
            @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveUserUseCase.save(userRequestDTO));
    }

    @Operation(summary = "List users with optional filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users listed successfully")
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> find(
            @ModelAttribute UserFilter userFilter) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findUserUseCase.find(userFilter));
    }

    @Operation(summary = "Find user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findUserByIdUseCase.findById(id));
    }

    @Operation(summary = "Update user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid fields"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateUserUseCase.update(id, userRequestDTO));
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "User ID", example = "1")
            @PathVariable Long id) {
        deleteUserUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find user by email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(
            @Parameter(description = "User email", example = "ana@email.com")
            @PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findByEmailUseCase.find(email));
    }

    @Operation(summary = "Find user by CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponseDTO> findByCpf(
            @Parameter(description = "User CPF", example = "123.456.789-01")
            @PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findByCpfUseCase.find(cpf));
    }
}