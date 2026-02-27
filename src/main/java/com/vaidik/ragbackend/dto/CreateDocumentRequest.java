package com.vaidik.ragbackend.dto;

// import java.time.LocalDateTime;

import com.vaidik.ragbackend.entity.DocumentTypes;

import lombok.Data;

@Data
public class CreateDocumentRequest {
    
    private String fileName;
    private String filePath;
    private DocumentTypes type;
    private String content;
    // private LocalDateTime createdAt;
    // private Long sizeInBytes;

}