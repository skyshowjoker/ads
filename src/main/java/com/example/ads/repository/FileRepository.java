package com.example.ads.repository;


import com.example.ads.domain.FileInfo;
import com.example.ads.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Integer> {
    FileInfo findById(long id);
}