package com.project.epharmacy.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespManufacturer {
    private Long id;
    private String name;
    private String address;
    private String email;
}
