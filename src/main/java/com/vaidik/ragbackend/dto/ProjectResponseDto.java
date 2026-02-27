package com.vaidik.ragbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.vaidik.ragbackend.entity.ProjectStatus;

@Data
@Builder
public class ProjectResponseDto {

    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDateTime createdAt;
}