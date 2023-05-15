package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespMedication;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/getMedicationList")
    public Response<List<RespMedication>> getMedicationList(@RequestBody ReqToken reqToken) {
        return medicationService.getMedicationList(reqToken);
    }
}