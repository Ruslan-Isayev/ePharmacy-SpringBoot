package com.project.epharmacy.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReqDepartment {

    private Long id;
    private String name;
    private String location;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
