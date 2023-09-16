package com.example.ads.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    Integer patientId;
    Date uploadDate;
    String filePath;
    String emzlResult;
    String progressiveResult;
    String activeInd;

}
