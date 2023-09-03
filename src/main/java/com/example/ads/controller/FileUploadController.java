package com.example.ads.controller;


import com.example.ads.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam(value="file", required = false) MultipartFile file, @RequestParam("patientId") Integer patientId) {
        if(file == null)
            return ResponseEntity.ok("Empty file");
;       String fileName = fileStorageService.storeFile(file, patientId);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }
}
