package com.example.ads.service.impl;

import com.example.ads.common.AdsContants;
import com.example.ads.entity.FileInfo;
import com.example.ads.repository.FileRepository;
import com.example.ads.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@Transactional
public class FileStorageService {

    @Value("${file.upload.path}")
    private String uploadPath;
    @Autowired
    FileRepository fileRepository;


    public String storeFile(MultipartFile file, Integer patientId) {
        try {
            String folder = uploadPath + File.separator + patientId;
            File dir = new File(folder);
            if(!dir.exists()){
                boolean result = dir.mkdir();
                if(result){
                    System.out.println("Folder creating successfully.");
                }
            }
            Path filePath = Paths.get(folder).resolve(getFileName(patientId));
            File saveFile = filePath.toFile();
            file.transferTo(saveFile);
            FileInfo fileInfo = fileRepository.findByPatientId(patientId);
            if(fileInfo == null){
                fileInfo = new FileInfo();
                fileInfo.setPatientId(patientId);
            }
            fileInfo.setFilePath(folder);
            fileInfo.setUploadDate(new Date());
            fileInfo.setActiveInd(AdsContants.ACTIVE);
            fileRepository.save(fileInfo);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file ");
        }
    }
    private String getFileName(Integer patientId){
        String fileName = "lymphoma_000_0000.nii.gz";
        return fileName;
    }
}
