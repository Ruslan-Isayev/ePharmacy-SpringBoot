package com.project.epharmacy.service;

import com.project.epharmacy.dto.request.ReqDepartment;
import com.project.epharmacy.dto.request.ReqManufacturer;
import com.project.epharmacy.dto.request.ReqMedication;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespMedication;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicationService {

    Response<List<RespMedication>> getMedicationList(ReqToken reqToken);

    Response<List<RespMedication>> getMedicationListByManufacturerId(ReqManufacturer reqManufacturer);

    Response<RespMedication> getMedicationById(ReqMedication reqMedication);

    Response<List<RespMedication>> getMedicationListByDepartmentId(ReqDepartment reqDepartment);

    Response addMedication(ReqMedication reqMedication);
}
