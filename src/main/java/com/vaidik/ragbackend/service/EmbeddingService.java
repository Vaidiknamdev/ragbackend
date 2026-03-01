package com.vaidik.ragbackend.service;

import java.util.List;

public interface EmbeddingService {
    List<Float> generateEmbedding(String text);
}