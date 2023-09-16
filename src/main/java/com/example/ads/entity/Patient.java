package com.example.ads.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Patient {

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

}
