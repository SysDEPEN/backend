package com.github.sysdepen.depen_api.repository;


import com.github.sysdepen.depen_api.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Documents, Long> {

}