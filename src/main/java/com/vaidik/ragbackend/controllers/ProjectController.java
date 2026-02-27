package com.vaidik.ragbackend.controllers;

import com.vaidik.ragbackend.dto.ProjectRequestDto;
import com.vaidik.ragbackend.dto.ProjectResponseDto;
import com.vaidik.ragbackend.dto.UpdateProjectStatusRequest;
import com.vaidik.ragbackend.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDto createProject(@Valid @RequestBody ProjectRequestDto request) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PatchMapping("/{id}/status")
    public ProjectResponseDto updateProjectStatus(@PathVariable Long id,@RequestBody UpdateProjectStatusRequest request)
    {
        return projectService.updateProjectStatus(id, request.getStatus());
    }
}
