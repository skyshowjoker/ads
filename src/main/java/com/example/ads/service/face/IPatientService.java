package com.example.ads.service.face;

import com.example.ads.dto.PatientDto;
import com.example.ads.entity.Patient;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface IPatientService {
    Long addNewPatient(PatientDto patientDto) throws ParseException;
    PatientDto getPatientDetail(Long patientId);
    ResponseEntity<String> predict(Long patientId);

    List<PatientDto> getAllPatient();
    String deletePatient(Long patientId);
}
