package com.example.crm.controller;

import com.example.crm.dto.TaskReq;
import com.example.crm.entity.Task;
import com.example.crm.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService svc;
    public TaskController(TaskService svc){this.svc=svc;}

    @PostMapping
    public Task create(@RequestBody TaskReq r){ return svc.create(r); }

    @GetMapping
    public Page<Task> list(@RequestParam(required=false) String status,
                           @RequestParam(required=false) Long assigneeId,
                           @RequestParam(required=false) Long clientId,
                           @RequestParam(required=false) String from,
                           @RequestParam(required=false) String to,
                           @RequestParam(defaultValue="0") int page,
                           @RequestParam(defaultValue="10") int size){
        LocalDate f=from==null?null:LocalDate.parse(from);
        LocalDate t=to==null?null:LocalDate.parse(to);
        return svc.filter(status,assigneeId,clientId,f,t,page,size);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id){ return svc.get(id); }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,@RequestBody TaskReq r){ return svc.update(id,r); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ svc.delete(id); }
}
