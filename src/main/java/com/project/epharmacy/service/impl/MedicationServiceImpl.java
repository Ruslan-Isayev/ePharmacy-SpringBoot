package com.project.epharmacy.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.epharmacy.dto.request.ReqDepartment;
import com.project.epharmacy.dto.request.ReqManufacturer;
import com.project.epharmacy.dto.request.ReqMedication;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespMedication;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Department;
import com.project.epharmacy.entity.Manufacturer;
import com.project.epharmacy.entity.Medication;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.DepartmentRepository;
import com.project.epharmacy.repository.ManufacturerRepository;
import com.project.epharmacy.repository.MedicationRepository;
import com.project.epharmacy.service.MedicationService;
import com.project.epharmacy.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final Utility utility;

    private final MedicationRepository medicationRepository;

    private final ManufacturerRepository manufacturerRepository;

    private final DepartmentRepository departmentRepository;


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

    @Override
    public Response<List<RespMedication>> getMedicationListByManufacturerId(ReqManufacturer reqManufacturer) {
        Response<List<RespMedication>> response = new Response<>();
        try {
            utility.checkToken(reqManufacturer.getReqToken());
            if (reqManufacturer.getId() == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Manufacturer manufacturer = manufacturerRepository.findManufacturerByIdAndActive(reqManufacturer.getId(), EnumAvavilableStatus.ACTIVE.value);
            if (manufacturer == null) {
                throw new MyException(ExceptionConstants.MANUFACTURER_NOT_FOUND, "Manufacturer not found");
            }
            List<Medication> medicationList = medicationRepository.findAllByManufacturerAndActive(manufacturer, EnumAvavilableStatus.ACTIVE.value);
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

    @Override
    public Response<RespMedication> getMedicationById(ReqMedication reqMedication) {
        Response<RespMedication> response = new Response<>();
        try {
            utility.checkToken(reqMedication.getReqToken());
            Long id = reqMedication.getId();
            if (id == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Medication medication = medicationRepository.findMedicationByIdAndActive(id, EnumAvavilableStatus.ACTIVE.value);
            if (medication == null) {
                throw new MyException(ExceptionConstants.MEDICATION_NOT_FOUND, "Medication not found");
            }
            RespMedication respMedication = mapping(medication);
            response.setT(respMedication);
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

    @Override
    public Response<List<RespMedication>> getMedicationListByDepartmentId(ReqDepartment reqDepartment) {
        Response<List<RespMedication>> response = new Response<>();
        try {
            utility.checkToken(reqDepartment.getReqToken());
            Long departmentId = reqDepartment.getId();
            if (departmentId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Department department = departmentRepository.getDepartmentByIdAndActive(departmentId, EnumAvavilableStatus.ACTIVE.value);
            if (department == null) {
                throw new MyException(ExceptionConstants.DEPARTMENT_NOT_FOUND, "Department not found");
            }
            List<Medication> medicationList = medicationRepository.findAllByDepartmentAndActive(department, EnumAvavilableStatus.ACTIVE.value);
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

    @Override
    public Response addMedication(ReqMedication reqMedication) {
        Response response = new Response<>();
        try {
            Long id;
            String name;
            Manufacturer manufacturer;
            ReqToken reqToken;
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
                .medicationId(medication.getId())
                .medicationName(medication.getName())
                .medicationPrice(medication.getPrice())
                .manufacturerName(medication.getManufacturer().getName())
                .productionDate(medication.getProductionDate())
                .expirationDate(medication.getExpirationDate())
                .build();
        return respMedication;
    }
}
