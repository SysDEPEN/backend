package com.github.sysdepen.depen_api.services;

import com.github.sysdepen.depen_api.entity.Login;
import com.github.sysdepen.depen_api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    public Login save(Login login) {
        return loginRepository.save(login);
    }

    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    public Optional<Login> findById(Long id) {
        return loginRepository.findById(id);
    }

    public Login update(Login login) {
        return loginRepository.save(login);
    }

    public void deleteById(Long id) {
        loginRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) loginRepository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome: " + username));
    }
}
