package com.example.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crm.entity.Lead;

public interface LeadRepo extends JpaRepository<Lead, Long> {
    Page<Lead> findByStatus(String status, Pageable pageable);
}
