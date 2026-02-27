package com.vaidik.ragbackend.repository;

import java.util.List;

// import javax.swing.text.Document;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidik.ragbackend.entity.Documents;

public interface DocumentRepository extends JpaRepository<Documents, Long> {

    List<Documents> findByProjectId(Long projectId);

    void deleteByProjectId(Long projectId);
}