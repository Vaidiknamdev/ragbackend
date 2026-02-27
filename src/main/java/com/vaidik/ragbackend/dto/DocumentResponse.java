package com.vaidik.ragbackend.dto;

// import java.time.Instant;
// import java.time.LocalDateTime;

import com.vaidik.ragbackend.entity.DocumentTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponse {

    private Long id;
    private Long projectId;
    private String fileName;
    private String filePath;
    private DocumentTypes type;
    // private LocalDateTime createdAt;
    
}