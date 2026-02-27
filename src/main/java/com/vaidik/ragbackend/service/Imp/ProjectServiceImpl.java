package com.vaidik.ragbackend.service.Imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaidik.ragbackend.dto.ProjectRequestDto;
import com.vaidik.ragbackend.dto.ProjectResponseDto;
import com.vaidik.ragbackend.entity.Project;
import com.vaidik.ragbackend.entity.ProjectStatus;
import com.vaidik.ragbackend.repository.ProjectRepository;
import com.vaidik.ragbackend.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto request) {

        if (projectRepository.existsByName(request.getName())) {
            throw new RuntimeException("Project with this name already exists");
        }

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(ProjectStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        Project saved = projectRepository.save(project);

        return mapToResponse(saved);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return mapToResponse(project);
    }
    
     @Override
    public Project getProjectOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
    }

    @Override
    public ProjectResponseDto updateProjectStatus(Long id, ProjectStatus status) {

        Project project = getProjectOrThrow(id);

        project.setStatus(status);

        Project updated = projectRepository.save(project);

        return mapToResponse(updated);
    }


    private ProjectResponseDto mapToResponse(Project project) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
