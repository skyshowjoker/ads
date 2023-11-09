package com.example.ads.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String name;
    String gender;
    Date birthday;
    @Column(name = "case_id")
    String caseId;
    String phone;
    String description;
    @Column(name = "active_ind")
    String activeInd;

}
