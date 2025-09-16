package com.example.demo.repository;

import com.example.demo.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepo extends JpaRepository<Lead, Long> {
}