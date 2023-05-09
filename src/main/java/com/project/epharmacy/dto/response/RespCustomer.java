package com.project.epharmacy.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespCustomer {

    private Long id;
    private String name;
    private String surname;
    private String cif;
    private Long phone;
    private Date dob;
}
