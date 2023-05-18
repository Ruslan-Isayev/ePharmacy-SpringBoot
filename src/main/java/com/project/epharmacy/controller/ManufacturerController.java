package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqMedication;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespManufacturer;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @PostMapping("/getManufacturerList")
    public Response<List<RespManufacturer>> getManufacturerList(@RequestBody ReqToken reqToken) {
        return manufacturerService.getManufacturerList(reqToken);
    }

    @PostMapping("/getManufacturerByMedicationId")
    public Response<RespManufacturer> getManufacturerByMedicationId(@RequestBody ReqMedication reqMedication) {
        return manufacturerService.getManufacturerByMedicationId(reqMedication);
    }
}
