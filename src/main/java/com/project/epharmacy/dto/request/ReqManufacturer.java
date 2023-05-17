package com.project.epharmacy.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReqManufacturer {

    private Long manufacturerId;
    private String name;
    private String address;
    private String email;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
