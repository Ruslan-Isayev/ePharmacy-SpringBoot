package com.project.epharmacy.service;

import com.project.epharmacy.dto.request.ReqMedication;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespManufacturer;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerService {

    Response<List<RespManufacturer>> getManufacturerList(ReqToken reqToken);

    Response<RespManufacturer> getManufacturerByMedicationId(ReqMedication reqMedication);
}
