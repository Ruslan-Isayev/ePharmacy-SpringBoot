package com.project.epharmacy.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespMedication {
    private Long medicationId;
    private String medicationName;
    private Double medicationPrice;
    private String manufacturerName;
    private Date productionDate;
    private Date expirationDate;
}
