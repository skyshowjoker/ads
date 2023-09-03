package com.example.ads.service;

import com.example.ads.domain.FileInfo;
import com.example.ads.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileStorageService {

    @Value("${file.upload.path}")
    private String uploadPath;
    @Autowired
    FileRepository fileRepository;

    public String storeFile(MultipartFile file, Integer patientId) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(getFileName(patientId));
            File saveFile = filePath.toFile();
            file.transferTo(saveFile);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath(filePath.toString());
            fileInfo.setPstientId(patientId);
            fileInfo.setUploadDate(new Date());
            fileRepository.save(fileInfo);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file ");
        }
    }
    private String getFileName(Integer patientId){
        String fileName = patientId + "_lymphoma_000_0000.nii.nz";
        return fileName;
    }
}
