package com.project.epharmacy.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqCustomer {

    private Long id;
    private String name;
    private String surname;
    private String cif;
    private Long phone;
    private Date dob;
}
