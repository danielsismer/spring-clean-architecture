package com.clean.architeture.domain.port;

import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepositoryPort {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll(UserFilter userFilter);
    boolean existByEmail(String email);
    void delete(Long id);

}
