package com.vaidik.ragbackend.repository;

// package com.v.ragbackend.project.repository;

// import com.v.ragbackend.project.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidik.ragbackend.entity.DocumentChunk;

import java.util.List;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, Long> {

    List<DocumentChunk> findByDocumentIdOrderByChunkIndexAsc(Long documentId);

    void deleteByDocumentId(Long documentId);
}