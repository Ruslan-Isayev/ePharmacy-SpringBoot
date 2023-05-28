package com.project.epharmacy.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqManufacturer {

    private Long id;
    private String name;
    private String address;
    private String email;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
