package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespMedication;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Medication;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.MedicationRepository;
import com.project.epharmacy.service.MedicationService;
import com.project.epharmacy.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final Utility utility;

    private final MedicationRepository medicationRepository;

    @Override
    public Response<List<RespMedication>> getMedicationList(ReqToken reqToken) {
        Response<List<RespMedication>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Medication> medicationList = medicationRepository.findAllByActive(EnumAvavilableStatus.ACTIVE.value);
            if (medicationList.isEmpty()) {
                throw new MyException(ExceptionConstants.MEDICATION_NOT_FOUND, "Medication not found");
            }
            List<RespMedication> respMedicationList = medicationList.stream().map(medication -> mapping(medication)).collect(Collectors.toList());
            response.setT(respMedicationList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespMedication mapping(Medication medication) {
        RespMedication respMedication = RespMedication.builder()
                .id(medication.getId())
                .name(medication.getName())
                .price(medication.getPrice())
                .productionDate(medication.getProductionDate())
                .expirationDate(medication.getExpirationDate())
                .manufacturer(medication.getManufacturer())
                .build();
        return respMedication;
    }
}
