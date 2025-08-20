package com.example.crm.repository;

import com.example.crm.entity.Lead;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends CrudRepository<Lead, Long> {
}