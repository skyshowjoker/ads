package com.example.ads.service.face;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    String storeFile(MultipartFile file, Integer patientId);
}
