package com.clean.architeture.infrastructure.persistence.user.spec;

import com.clean.architeture.domain.dto.filter.UserFilter;
import com.clean.architeture.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpec {

    public static Specification<User> filter(UserFilter userFilter){
        return Specification
                .where(filterByCpf(userFilter.cpf()))
                .and(filterByEmail(userFilter.email()));
    }

    private static Specification<User> filterByEmail(String email){
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isBlank()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%"+email.toLowerCase()+"%");
        };
    }

    private static Specification<User> filterByCpf(String cpf){
        return (root, query, criteriaBuilder) -> {
            if(cpf == null || cpf.isBlank()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("cpf")), "%"+cpf.toLowerCase()+"%");
        };
    }
}
