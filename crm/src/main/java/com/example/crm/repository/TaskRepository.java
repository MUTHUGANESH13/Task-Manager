package com.example.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}