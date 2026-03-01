package com.vaidik.ragbackend.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.vaidik.ragbackend.service.EmbeddingService;

@Service
public class MockEmbeddingService implements EmbeddingService {

    private static final int DIMENSION = 384; // or your desired embedding size
    private final Random random = new Random();

    @Override
    public List<Float> generateEmbedding(String text) {
        List<Float> vector = new ArrayList<>(DIMENSION);
        for (int i = 0; i < DIMENSION; i++) {
            vector.add(random.nextFloat());
        }
        return vector;
    }
}