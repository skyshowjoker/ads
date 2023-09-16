package com.example.ads.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class PatientDto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;
    String name;
    String gender;
    String birthday;
    String caseId;
    String phone;
    String email;
    String description;
    String activeInd;
    Date uploadDate;
    String emzlResult;
    String ProgressiveResult;

}
