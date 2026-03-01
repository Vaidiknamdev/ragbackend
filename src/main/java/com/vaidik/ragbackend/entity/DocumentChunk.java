package com.vaidik.ragbackend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "document_chunks")
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Documents document;

    @Column(nullable = false)
    private Integer chunkIndex;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String chunkText;

   @Lob
   @Column(columnDefinition = "json")
   private String embedding;

    // Constructors
    public DocumentChunk() {}

    public DocumentChunk(Documents document, Integer chunkIndex, String chunkText,String embedding) {
        this.document = document;
        this.chunkIndex = chunkIndex;
        this.chunkText = chunkText;
        this.embedding = embedding;
    }
    public DocumentChunk(Documents document, Integer chunkIndex, String chunkText) {
        this.document = document;
        this.chunkIndex = chunkIndex;
        this.chunkText = chunkText;
        
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Documents getDocument() {
        return document;
    }

    public void setDocument(Documents document) {
        this.document = document;
    }

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public String getChunkText() {
        return chunkText;
    }

    public void setChunkText(String chunkText) {
        this.chunkText = chunkText;
    }

    public String getEmbedding() {
       return embedding;
    }

    public void setEmbedding(String embedding) {
       this.embedding = embedding;
    }
}