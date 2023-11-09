package com.example.ads.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @Column(name = "patient_id")
    Integer patientId;
    @Column(name = "upload_date")
    Date uploadDate;
    @Column(name = "file_path")
    String filePath;
    @Column(name = "emzl_result")
    String emzlResult;
    @Column(name = "progressive_result")
    String progressiveResult;
    @Column(name = "active_ind")
    String activeInd;

}
