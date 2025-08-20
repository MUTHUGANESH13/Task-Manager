package com.example.crm.controller;

import com.example.crm.entity.Task;
import com.example.crm.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskWebController {
    private final TaskRepository taskRepository;

    public TaskWebController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String dueDate) {
        Task t = new Task();
        t.setTitle(title);
        t.setDescription(description);
        t.setDueDate(java.sql.Date.valueOf(dueDate));
        t.setStatus(Task.Status.PENDING);
        taskRepository.save(t);
        return "redirect:/tasks";
    }
}
