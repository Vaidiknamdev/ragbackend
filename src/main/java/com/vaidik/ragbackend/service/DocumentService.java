package com.vaidik.ragbackend.service;

// import java.time.LocalDateTime;
import java.util.List;

import com.vaidik.ragbackend.entity.Documents;
import com.vaidik.ragbackend.entity.DocumentTypes;

public interface DocumentService {
     
    Documents createDocument(
            Long projectId,
            String fileName,
            String filePath,
            DocumentTypes type,
            String content
            
            
    );

    List<Documents> getDocumentsByProject(Long projectId);

    void deleteDocumentsByProject(Long projectId);
}