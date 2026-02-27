package com.vaidik.ragbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequestDto {

    @NotBlank(message = "Project name is required")
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;
}
