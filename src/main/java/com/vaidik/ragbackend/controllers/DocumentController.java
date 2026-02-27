package com.vaidik.ragbackend.controllers;



import lombok.RequiredArgsConstructor;
// import lombok.Setter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaidik.ragbackend.dto.CreateDocumentRequest;
import com.vaidik.ragbackend.dto.DocumentResponse;
import com.vaidik.ragbackend.entity.Documents;
import com.vaidik.ragbackend.service.DocumentService;

// import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/projects/{projectId}/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    // CREATE DOCUMENT
    @PostMapping
    public ResponseEntity<DocumentResponse> createDocument(
            @PathVariable Long projectId,
            @RequestBody CreateDocumentRequest request
    ) {

        Documents documents = documentService.createDocument(
                projectId,
                request.getFileName(),
                request.getFilePath(),
                request.getType(),
                request.getContent()
                // request.getCreatedAt()
                
        );

        return ResponseEntity.ok(mapToResponse(documents));
    }

    // GET ALL DOCUMENTS OF A PROJECT
    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getDocuments(
            @PathVariable Long projectId
    ) {

        List<DocumentResponse> response = documentService
                .getDocumentsByProject(projectId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // PRIVATE MAPPER METHOD
    private DocumentResponse mapToResponse(Documents document) {
        return DocumentResponse.builder()
                .id(document.getId())
                .projectId(document.getProject().getId())
                .fileName(document.getFileName())
                .filePath(document.getFilePath())
                .type(document.getType())
                // .createdAt(document.getCreatedAt())
                .build();
    }
}