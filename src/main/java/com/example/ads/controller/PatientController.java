package com.example.ads.controller;



import com.example.ads.dto.ApiResponse;
import com.example.ads.dto.PatientDto;
import com.example.ads.entity.FileInfo;
import com.example.ads.entity.Patient;
import com.example.ads.repository.FileRepository;
import com.example.ads.repository.PatientRepository;
import com.example.ads.service.impl.PatientService;
import com.example.ads.utils.PythonScriptInvokeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    PatientService patientService;
    @CrossOrigin(origins = "https://ads.testop.top")
    @PostMapping(path="/save") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Long> addNewPatient (@RequestBody PatientDto patientDto) throws ParseException {
        return ResponseEntity.ok(patientService.addNewPatient(patientDto));
    }
    @GetMapping(path="/delete") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<String> deletePatient (@RequestParam("patientId") Long patientId){
        return ResponseEntity.ok(patientService.deletePatient(patientId));
    }


    @GetMapping(path="/predict")
    public @ResponseBody ResponseEntity<ApiResponse> predict(@RequestParam("patientId") Long patientId) {
        Boolean result = patientService.predict(patientId);
        ApiResponse apiResponse;
        if(result){
            apiResponse = new ApiResponse("200", "success");
        }else{
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<ApiResponse> getAllPatients() {
        // This returns a JSON or XML with the Patients
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.toString(), "success", patientService.getAllPatient());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path="/getDetailById")
    public @ResponseBody ResponseEntity<PatientDto> getPatient(@RequestParam("id")Long id) {
        // This returns a JSON or XML with the Patients
        return ResponseEntity.ok(patientService.getPatientDetail(id));
    }
}