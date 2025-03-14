package com.github.sysdepen.depen_api.controller;


import com.github.sysdepen.depen_api.DTOs.LoginDto;
import com.github.sysdepen.depen_api.entity.Login;
import com.github.sysdepen.depen_api.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sysdepen.depen_api.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> getJwtByLogin(@RequestBody LoginDto login){
        try {
            var token = authService.login(login);
            Map<String, String> response = new HashMap<>();
            response.put("Token", token);

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(response);

            return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @GetMapping("logins")
    public ResponseEntity<List<Login>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Login>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        loginService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
