package com.project.epharmacy.dto.response;

import com.project.epharmacy.entity.Manufacturer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespMedication {
    private Long id;
    private String name;
    private Double price;
    private Date productionDate;
    private Date expirationDate;
    private Manufacturer manufacturer;
}
