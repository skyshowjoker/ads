package com.example.ads.service.impl;

import com.example.ads.common.AdsContants;
import com.example.ads.dto.PatientDto;
import com.example.ads.entity.FileInfo;
import com.example.ads.entity.Patient;
import com.example.ads.repository.FileRepository;
import com.example.ads.repository.PatientRepository;
import com.example.ads.service.face.IPatientService;
import com.example.ads.utils.DateUtils;
import com.example.ads.utils.PythonScriptInvokeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    FileRepository fileRepository;

    public static Map<String, String> EMZL_MAP = Map.of("0", AdsContants.EMZL, "1", AdsContants.NON_EMZL);
    public static Map<String, String> MATURE_MAP = Map.of("0", AdsContants.PROGRESSIVE, "1", AdsContants.INDOLENT);
    @Override
    public Long addNewPatient(PatientDto patientDto) throws ParseException {
        Patient patient = convertPatientDtoToEntity(patientDto);
        patient.setActiveInd(AdsContants.ACTIVE);
        patient = patientRepository.save(patient);
        return patient.getId();
    }
    private Patient convertPatientDtoToEntity(PatientDto patientDto) throws ParseException {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDto, patient);
        patient.setBirthday(DateUtils.parseDate(patientDto.getBirthday()));
        return patient;
    }

    private PatientDto convertPatientEntityToDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(patient, patientDto);
        patientDto.setBirthday(DateUtils.formatDate(patient.getBirthday()));
        FileInfo fileInfo = fileRepository.findByPatientId(patient.getId());
        if(Objects.nonNull(fileInfo)){
            BeanUtils.copyProperties(fileInfo, patientDto);
            patientDto.setUploadDate(DateUtils.formatDate(fileInfo.getUploadDate()));
        }
        return patientDto;
    }

    @Override
    public PatientDto getPatientDetail(Long patientId){
        Patient patient = patientRepository.findById(patientId);
        PatientDto patientDto = convertPatientEntityToDto(patient);
        return patientDto;
    }

    @Override
    public Boolean predict(Long patientId){
        FileInfo fileInfo = fileRepository.findByPatientId(patientId);
        if(Objects.nonNull(fileInfo)){
            try{
                Object result = PythonScriptInvokeUtil.invoke(fileInfo.getFilePath());
                fileInfo.setEmzlResult(EMZL_MAP.get(result.toString()));
                fileInfo.setProgressiveResult(MATURE_MAP.get(result.toString()));
                fileRepository.save(fileInfo);
            }catch (Exception e){
                fileInfo.setEmzlResult(EMZL_MAP.get("0"));
                fileInfo.setProgressiveResult(MATURE_MAP.get("0"));
                fileRepository.save(fileInfo);
            }
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }

    }

    @Override
    public List<PatientDto> getAllPatient() {
        return patientRepository.findAll()
                .stream()
                .filter(patient -> AdsContants.ACTIVE.equals(patient.getActiveInd()))
                .map(this::convertPatientEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public String deletePatient(Long patientId){
        FileInfo fileInfo = fileRepository.findByPatientId(patientId);
        if(Objects.nonNull(fileInfo)){
            fileInfo.setActiveInd(AdsContants.INACTIVE);
            fileRepository.save(fileInfo);
        }

        Patient patient = patientRepository.findById(patientId);
        patient.setActiveInd(AdsContants.INACTIVE);
        patientRepository.save(patient);
        return "delete success";
    }
}
