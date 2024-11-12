package com.github.sysdepen.depen_api.repository;

import com.github.sysdepen.depen_api.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByNome(String nome);
}
