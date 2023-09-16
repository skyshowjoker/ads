package com.example.ads.repository;


import com.example.ads.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Integer> {
    FileInfo findByPatientId(long id);
}
