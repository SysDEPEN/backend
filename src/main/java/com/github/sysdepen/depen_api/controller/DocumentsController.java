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
    public ResponseEntity<?> uploadFiles(@RequestParam("userName") String userName,
                                         @RequestParam("docs") List<Documents> docs) throws IOException {
        List<Documents> savedDocuments = new ArrayList<>();

        for (MultipartFile file : docs) {
            // Define o nome e o caminho do arquivo
            String fileName = file.getOriginalFilename();
            File targetFile = new File("uploads/" + userName + "/" + documentType + "/" + fileName);
            targetFile.getParentFile().mkdirs(); // Cria o diretório se não existir

            // Salva o arquivo no sistema de arquivos
            file.transferTo(targetFile);

            // Salva o caminho do arquivo no banco de dados
            Documents userDocument = documentService.save(userName, documentType, fileName);
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
