package com.github.sysdepen.depen_api.services;



import com.github.sysdepen.depen_api.DTOs.LoginDto;
import com.github.sysdepen.depen_api.entity.User;
import com.github.sysdepen.depen_api.repository.UserRepository;
import com.github.sysdepen.depen_api.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private UserRepository userRepository;

    public String login(LoginDto usuario) {
        User user = userRepository.findByDocument(usuario.getDocument());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        if (!user.getPassword().equals(usuario.getPassword())) {
            throw new RuntimeException("Usuario ou Senha inválida");
        }
        //return user.getId().toString();
        var token = getToken(user);
        return token;
    }

    public String getToken(User user) {
        return jwt.generate(user, "ACCESS");
    }
}
