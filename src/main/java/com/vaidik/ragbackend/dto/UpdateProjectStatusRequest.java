package com.vaidik.ragbackend.dto;
import com.vaidik.ragbackend.entity.ProjectStatus;

import lombok.Data;

@Data
public class UpdateProjectStatusRequest {

    private ProjectStatus status;

}