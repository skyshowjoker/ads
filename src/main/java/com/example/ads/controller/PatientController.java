package com.example.ads.controller;



import com.example.ads.entity.FileInfo;
import com.example.ads.entity.Patient;
import com.example.ads.repository.FileRepository;
import com.example.ads.repository.PatientRepository;
import com.example.ads.utils.PythonScriptInvokeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    FileRepository fileRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewPatient (@RequestBody Patient patient){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        patientRepository.save(patient);
        return "Saved";
    }

    @GetMapping(path="/predict")
    public @ResponseBody ResponseEntity<String> predict(@RequestParam("patientId") Integer patientId) {
        FileInfo fileInfo = fileRepository.findByPatientId(patientId);
        Object result = PythonScriptInvokeUtil.invoke(fileInfo.getFilePath());
        fileInfo.setEmzlResult(result.toString());
        fileRepository.save(fileInfo);
        return ResponseEntity.ok("Predict success");
    }
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<Patient>> getAllPatients() {
        // This returns a JSON or XML with the Patients
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping(path="/getDetailById")
    public @ResponseBody ResponseEntity<Patient> getPatient(@RequestParam("id")Integer id) {
        // This returns a JSON or XML with the Patients
        return ResponseEntity.ok(patientRepository.findById(id).get());
    }
}