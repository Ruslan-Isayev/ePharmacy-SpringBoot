package com.project.epharmacy.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqCustomer {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String cif;
    private Long phone;
    private Date dob;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
