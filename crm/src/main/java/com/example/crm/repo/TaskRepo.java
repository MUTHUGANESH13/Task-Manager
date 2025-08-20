package com.example.crm.repo;

import com.example.crm.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import java.time.*;

public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("select t from Task t where (:status is null or t.status=:status) " +
           "and (:assigneeId is null or t.assignee.id=:assigneeId) " +
           "and (:clientId is null or t.client.id=:clientId) " +
           "and (:from is null or t.dueDate>=:from) " +
           "and (:to is null or t.dueDate<=:to)")
    Page<Task> filter(TaskStatus status, Long assigneeId, Long clientId, LocalDate from, LocalDate to, Pageable pageable);
}
