package com.vaidik.ragbackend.service.Imp;

// import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaidik.ragbackend.entity.Documents;
import com.vaidik.ragbackend.entity.DocumentTypes;
import com.vaidik.ragbackend.entity.Project;
import com.vaidik.ragbackend.repository.DocumentRepository;
import com.vaidik.ragbackend.repository.ProjectRepository;
import com.vaidik.ragbackend.service.DocumentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Documents createDocument(
            Long projectId,
            String fileName,
            String filePath,
            DocumentTypes type,
            String content
            
            
    ) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Documents document = new Documents();
        document.setProject(project);
        document.setFileName(fileName);
        document.setFilePath(filePath);
        document.setType(type);
        document.setContent(content);
        // document.setCreatedAt(createdAt);
        // document.setSizeInBytes(sizeInBytes);

        return documentRepository.save(document);
    }

    @Override
    @Transactional
    public List<Documents> getDocumentsByProject(Long projectId) {
        return documentRepository.findByProjectId(projectId);
    }

    @Override
    public void deleteDocumentsByProject(Long projectId) {
        List<Documents> documents = documentRepository.findByProjectId(projectId);
        documentRepository.deleteAll(documents);
    }
}