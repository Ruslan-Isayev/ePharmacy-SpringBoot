package com.project.epharmacy.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.epharmacy.entity.Manufacturer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqMedication {
    private Long id;
    private String name;
    private Double price;
    private Date productionDate;
    private Date expirationDate;
    private Manufacturer manufacturer;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
