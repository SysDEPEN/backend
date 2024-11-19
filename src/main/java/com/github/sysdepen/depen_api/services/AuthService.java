package com.github.sysdepen.depen_api.services;

import com.github.sysdepen.depen_api.DTOs.LoginDto;
import com.github.sysdepen.depen_api.entity.User;
import com.github.sysdepen.depen_api.repository.UserRepository;
import com.github.sysdepen.depen_api.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto usuario) {
        User user = userRepository.findByDocument(usuario.getDocument());
        if (user == null) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        if (!passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        return jwt.generate(user, "ACCESS");
    }
}
