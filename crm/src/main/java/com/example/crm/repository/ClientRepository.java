package com.example.crm.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.crm.entity.Client; // Change to your actual Client entity package

public interface ClientRepository extends CrudRepository<Client, Long> {
}
