package com.clean.architeture.infrastructure.persistence.user;

import com.clean.architeture.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository
        extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

}