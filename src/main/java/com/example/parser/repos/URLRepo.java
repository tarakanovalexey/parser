package com.example.parser.repos;

import com.example.parser.domain.ParseURL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface URLRepo extends JpaRepository<ParseURL, Long> {

    List<ParseURL> findByUserId(Long userId);
}
