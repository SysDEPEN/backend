package com.github.sysdepen.depen_api.repository;


import com.github.sysdepen.depen_api.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Documents, Long> {
    List<Documents> findByUserId(Long userId);
}
