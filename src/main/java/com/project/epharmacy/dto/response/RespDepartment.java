package com.project.epharmacy.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespDepartment {
    private Long id;
    private String name;
    private String location;
}
