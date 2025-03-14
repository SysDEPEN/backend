package com.github.sysdepen.depen_api.controller;


import com.github.sysdepen.depen_api.entity.RequerimentoInfo;
import com.github.sysdepen.depen_api.services.RequerimentosInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/req_camp")
public class RequerimentoInfoController {

    @Autowired
    private RequerimentosInfoService requerimentoInfoService;

    @GetMapping
    public ResponseEntity<List<RequerimentoInfo>> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(requerimentoInfoService.findAll());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RequerimentoInfo>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(requerimentoInfoService.findById(id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RequerimentoInfo> create(@RequestBody @Valid RequerimentoInfo requerimentoInfo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(requerimentoInfoService.save(requerimentoInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequerimentoInfo> update(@PathVariable Long id, @RequestBody RequerimentoInfo requerimentoInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(requerimentoInfoService.update(id, requerimentoInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            requerimentoInfoService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
