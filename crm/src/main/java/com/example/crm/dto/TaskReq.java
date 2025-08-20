package com.example.crm.dto;

import java.time.LocalDate;

public record TaskReq(String title, String description, String status, Long clientId, Long assigneeId, LocalDate dueDate) {}
