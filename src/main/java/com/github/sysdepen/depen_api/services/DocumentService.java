package com.github.sysdepen.depen_api.services;


import com.github.sysdepen.depen_api.entity.Documents;
import com.github.sysdepen.depen_api.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;


    public Documents save(String userName, String documentType, String fileName) {

        try{
            // Define o caminho do arquivo com base no tipo de documento
            String filePath = "uploads/" + userName + "/" + documentType + "/" + fileName;

            // Cria a entidade e salva no banco
            Documents userDocument = new Documents();
            userDocument.set(userName);
            userDocument.setFilePath(filePath);
            userDocument.setDocumentType(documentType);

            return documentRepository.save(userDocument);
        }
        catch (Exception e){
            throw new RuntimeException("document not saved");
        }
    }


    public List<Documents> findAll() {
            return documentRepository.findAll();
    }


    public Optional<Documents> findById(Long id) {
        if(documentRepository.findById(id).isEmpty()){
            throw new RuntimeException("document not found with id " + id);
        }
        return documentRepository.findById(id);
    }


    public Documents update(Documents documents) {
        return null;
    }


    public void deleteById(Long id) {

    }
}
