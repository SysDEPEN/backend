package com.github.sysdepen.depen_api.controller;

import java.util.List;
import java.util.Optional;

import com.github.sysdepen.depen_api.entity.Protocols;
import com.github.sysdepen.depen_api.services.ProtocoloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("api/v1/protocols")
public class ProtocolController {
    @Autowired
    private ProtocoloService protocoloService;

    @GetMapping
    public ResponseEntity<List<Protocols>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(protocoloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Protocols>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(protocoloService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Protocols> create(@RequestBody @Valid Protocols protocols) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(protocoloService.save(protocols));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Protocols> update(@RequestBody Protocols protocols) {
        return ResponseEntity.status(HttpStatus.OK).body(protocoloService.update(protocols));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        protocoloService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
