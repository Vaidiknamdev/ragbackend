package com.vaidik.ragbackend.entity;

// import java.time.Instant;
// import java.time.LocalDateTime;

// import org.w3c.dom.DocumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "documents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentTypes type;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // private Long sizeInBytes;

    // private Instant createdAt = Instant.now();
    //  @Column(nullable = false, updatable = false)
    // private LocalDateTime createdAt;
}