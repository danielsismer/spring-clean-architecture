package com.clean.architeture.infrastructure.persistence.user;

import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.entity.User;
import com.clean.architeture.domain.port.UserRepositoryPort;
import com.clean.architeture.infrastructure.persistence.user.spec.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public User save(User user) {
        return usuarioJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return usuarioJpaRepository.findById(id);
    }

    @Override
    public List<User> findAll(UserFilter userFilter) {
        return usuarioJpaRepository.findAll(UserSpec.filter(userFilter));
    }

    @Override
    public boolean existByEmail(String email) {
        return usuarioJpaRepository.existsByEmail(email);
    }

    @Override
    public void delete(Long id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return usuarioJpaRepository.findByCpf(cpf);
    }
}
