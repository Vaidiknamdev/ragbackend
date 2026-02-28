package com.vaidik.ragbackend.service.Imp;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaidik.ragbackend.entity.DocumentChunk;
import com.vaidik.ragbackend.entity.Documents;
import com.vaidik.ragbackend.repository.DocumentChunkRepository;
import com.vaidik.ragbackend.service.ChunkService;

@Service
public class ChunkServiceImpl implements ChunkService {

    private final DocumentChunkRepository chunkRepository;

    // You can later externalize these to application.properties
    private static final int CHUNK_SIZE = 1000;     // characters
    private static final int CHUNK_OVERLAP = 200;   // characters

    public ChunkServiceImpl(DocumentChunkRepository chunkRepository) {
        this.chunkRepository = chunkRepository;
    }

    @Override
    @Transactional
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

            DocumentChunk chunk = new DocumentChunk(
                    document,
                    chunkIndex,
                    chunkText
            );

            chunkRepository.save(chunk);

            chunkIndex++;

            // Sliding window movement
            start += (CHUNK_SIZE - CHUNK_OVERLAP);
        }
    }
}