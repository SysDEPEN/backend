package com.github.sysdepen.depen_api.controller;


import com.github.sysdepen.depen_api.entity.Documents;
import com.github.sysdepen.depen_api.entity.User;
import com.github.sysdepen.depen_api.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/documents")
public class DocumentsController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Documents>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Documents>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(documentService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("userId") Long userId,
                                         @RequestParam("files") List<MultipartFile> files,
                                         @RequestParam("documentType") String documentType) throws IOException {
        // Define o diretório base para o usuário
        String baseDir = System.getProperty("user.dir") + "/uploads/";
        File userDir = new File(baseDir + userId);

        // Verifica se a pasta do userId já existe
        if (!userDir.exists()) {
            userDir.mkdirs(); // Cria a pasta do userId, se não existir
        }

        // Define a subpasta para o tipo de documento
        File documentTypeDir = new File(userDir, documentType);

        // Verifica se a pasta do tipo de documento já existe
        if (documentTypeDir.exists()) {
            return ResponseEntity.badRequest().body("O tipo de documento '" + documentType + "' já foi enviado para este usuário.");
        }

        // Cria a subpasta para o tipo de documento
        documentTypeDir.mkdirs();

        List<Documents> savedDocuments = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File targetFile = new File(documentTypeDir, fileName);

            // Transfere o arquivo para o sistema de arquivos
            file.transferTo(targetFile);

            // Salva o caminho do arquivo no banco de dados
            Documents userDocument = documentService.save(userId, documentType, fileName);
            savedDocuments.add(userDocument);
        }

        return ResponseEntity.ok(savedDocuments);
    }

    @PutMapping
    public ResponseEntity<Documents> update(@RequestBody Documents documents) {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.update(documents));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        documentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
