package com.github.sysdepen.depen_api.services;


import com.github.sysdepen.depen_api.entity.User;
import com.github.sysdepen.depen_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        try{
            return userRepository.save(user);
        }
        catch (Exception e){
//            throw new RuntimeException("User not saved, because: " + e.getMessage());
            throw new IllegalArgumentException("User not saved because: " + e.getMessage());
        }
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new RuntimeException("User not found with id " + id);
        }
        return userRepository.findById(id);
    }

    public User update(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName() != null ? updatedUser.getName() : user.getName());
            user.setPassword(updatedUser.getPassword() != null ? updatedUser.getPassword() : user.getPassword());
            user.setRole(updatedUser.getRole() != null ? updatedUser.getRole() : user.getRole());
            user.setDocument(updatedUser.getDocument() != null ? updatedUser.getDocument() : user.getDocument());
            user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
            user.setGender(updatedUser.getGender() != null ? updatedUser.getGender() : user.getGender());
            user.setUpdated_at(LocalDateTime.now());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
