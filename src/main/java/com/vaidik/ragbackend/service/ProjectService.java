package com.vaidik.ragbackend.service;


import java.util.List;

import com.vaidik.ragbackend.dto.ProjectRequestDto;
import com.vaidik.ragbackend.dto.ProjectResponseDto;
import com.vaidik.ragbackend.entity.Project;
import com.vaidik.ragbackend.entity.ProjectStatus;

public interface ProjectService {

    ProjectResponseDto createProject(ProjectRequestDto request);

    List<ProjectResponseDto> getAllProjects();

    ProjectResponseDto getProjectById(Long id);

    Project getProjectOrThrow(Long id);

    ProjectResponseDto updateProjectStatus(Long id, ProjectStatus status);
}