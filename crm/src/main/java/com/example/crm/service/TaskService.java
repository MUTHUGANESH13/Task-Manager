package com.example.crm.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.dto.TaskReq;
import com.example.crm.entity.Task;
import com.example.crm.entity.TaskStatus;
import com.example.crm.repo.ClientRepo;
import com.example.crm.repo.TaskRepo;
import com.example.crm.repo.UserRepo;

@Service
public class TaskService {
    private final TaskRepo repo;
    private final ClientRepo clients;
    private final UserRepo users;

    public TaskService(TaskRepo r, ClientRepo c, UserRepo u){this.repo=r;this.clients=c;this.users=u;}

    public Task create(TaskReq r){
        Task t=new Task();
        t.setTitle(r.title());
        t.setDescription(r.description());
        if(r.status()!=null) t.setStatus(Task.Status.valueOf(r.status()));
        if (r.dueDate() != null) {
            t.setDueDate(java.sql.Date.valueOf(r.dueDate()));
        } else {
            t.setDueDate(null);
        }
        if(r.clientId()!=null) t.setClient(clients.findById(r.clientId()).orElseThrow());
        if(r.assigneeId()!=null) t.setAssignee(users.findById(r.assigneeId()).orElseThrow());
        return repo.save(t);
    }

    public Page<Task> filter(String status,Long assigneeId,Long clientId,LocalDate from,LocalDate to,int page,int size){
        TaskStatus st=status==null?null:TaskStatus.valueOf(status);
        return repo.filter(st,assigneeId,clientId,from,to,PageRequest.of(page,size));
    }

    public Task get(Long id){ return repo.findById(id).orElseThrow(); }

    public Task update(Long id,TaskReq r){
        Task t=get(id);
        t.setTitle(r.title());
        t.setDescription(r.description());
        if(r.status()!=null) t.setStatus(Task.Status.valueOf(r.status()));
        t.setDueDate(r.dueDate() != null ? java.sql.Date.valueOf(r.dueDate()) : null);
        if(r.clientId()!=null) t.setClient(clients.findById(r.clientId()).orElseThrow());
        if(r.assigneeId()!=null) t.setAssignee(users.findById(r.assigneeId()).orElseThrow());
        return repo.save(t);
    }

    public void delete(Long id){ repo.deleteById(id); }
}
