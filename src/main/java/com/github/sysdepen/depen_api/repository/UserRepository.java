package com.github.sysdepen.depen_api.repository;


import com.github.sysdepen.depen_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

     User findByDocument(String document);
}