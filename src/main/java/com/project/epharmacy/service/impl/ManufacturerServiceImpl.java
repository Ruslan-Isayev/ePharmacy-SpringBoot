package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.request.ReqManufacturer;
import com.project.epharmacy.dto.request.ReqMedication;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespManufacturer;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Manufacturer;
import com.project.epharmacy.entity.Medication;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.ManufacturerRepository;
import com.project.epharmacy.repository.MedicationRepository;
import com.project.epharmacy.service.ManufacturerService;
import com.project.epharmacy.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final MedicationRepository medicationRepository;

    private final Utility utility;

    @Override
    public Response<List<RespManufacturer>> getManufacturerList(ReqToken reqToken) {
        Response<List<RespManufacturer>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
            List<Manufacturer> manufacturerList = manufacturerRepository.findAllByActive(EnumAvavilableStatus.ACTIVE.value);
            if (manufacturerList.isEmpty()) {
                throw new MyException(ExceptionConstants.MANUFACTURER_NOT_FOUND, "Manufacturer not found");
            }
            List<RespManufacturer> respManufacturerList = manufacturerList.stream().map(manufacturer -> mapping(manufacturer)).collect(Collectors.toList());
            response.setT(respManufacturerList);
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
    public Response<RespManufacturer> getManufacturerByMedicationId(ReqMedication reqMedication) {
        Response<RespManufacturer> response = new Response<>();
        try {
            utility.checkToken(reqMedication.getReqToken());
            if (reqMedication.getId() == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Medication medication = medicationRepository.findMedicationByIdAndActive(reqMedication.getId(), EnumAvavilableStatus.ACTIVE.value);
            if (medication == null) {
                throw new MyException(ExceptionConstants.MEDICATION_NOT_FOUND, "Medication not found");
            }
            Manufacturer manufacturer = manufacturerRepository.findManufacturerByMedicationAndActive(medication, EnumAvavilableStatus.ACTIVE.value);
            if (manufacturer == null) {
                throw new MyException(ExceptionConstants.MANUFACTURER_NOT_FOUND, "Manufacturer not found");
            }
            RespManufacturer respManufacturer = mapping(manufacturer);
            response.setT(respManufacturer);
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
    public Response addManufacturer(ReqManufacturer reqManufacturer) {
        Response response = new Response<>();
        try {
            String name = reqManufacturer.getName();
            String address = reqManufacturer.getAddress();
            if (name == null || address == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Manufacturer manufacturer = Manufacturer.builder()
                    .name(name)
                    .address(address)
                    .email(reqManufacturer.getEmail())
                    .build();
            manufacturerRepository.save(manufacturer);
            response.setStatus(RespStatus.getSuccessMessage());
        }  catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespManufacturer mapping(Manufacturer manufacturer) {
        RespManufacturer respManufacturer = RespManufacturer.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .address(manufacturer.getAddress())
                .email(manufacturer.getEmail())
                .build();
        return respManufacturer;
    }
}
