package com.example.parser.repos;

import com.example.parser.domain.Resumes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResumesRepo extends JpaRepository<Resumes, Long> {
    List<Resumes> findBySearchId(Long searchId);
}
