package com.vaidik.ragbackend.service.Imp;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaidik.ragbackend.entity.DocumentChunk;
import com.vaidik.ragbackend.entity.Documents;
import com.vaidik.ragbackend.repository.DocumentChunkRepository;
import com.vaidik.ragbackend.service.ChunkService;
import com.vaidik.ragbackend.service.EmbeddingService;

import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChunkServiceImpl implements ChunkService {

    private final DocumentChunkRepository chunkRepository;
    private final EmbeddingService embeddingService;
    private final ObjectMapper objectMapper;

    // You can later externalize these to application.properties
    private static final int CHUNK_SIZE = 1000;     // characters
    private static final int CHUNK_OVERLAP = 200;   // characters

    @Override
    public void createChunks(Documents document) {

        // Remove old chunks if re-processing
        chunkRepository.deleteByDocumentId(document.getId());

        String content = document.getContent();
        if (content == null || content.isBlank()) {
            return;
        }

        int contentLength = content.length();
        int start = 0;
        int chunkIndex = 0;

        while (start < contentLength) {
            int end = Math.min(start + CHUNK_SIZE, contentLength);
            String chunkText = content.substring(start, end);

            DocumentChunk chunk = new DocumentChunk(document, chunkIndex, chunkText);

            // Generate embedding for this chunk
            try {
                List<Float> vector = embeddingService.generateEmbedding(chunkText);
                String embeddingJson = objectMapper.writeValueAsString(vector);
                chunk.setEmbedding(embeddingJson);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate or serialize embedding", e);
            }

            // Save chunk with text + embedding
            chunkRepository.save(chunk);

            chunkIndex++;
            // Sliding window movement
            start += (CHUNK_SIZE - CHUNK_OVERLAP);
        }
    }
}