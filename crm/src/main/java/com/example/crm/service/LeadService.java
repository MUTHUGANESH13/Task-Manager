package com.example.crm.service;

import com.example.crm.dto.LeadReq;
import com.example.crm.entity.*;
import com.example.crm.repo.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
    private final LeadRepo repo;
    private final ClientRepo clients;

    public LeadService(LeadRepo repo, ClientRepo clients){this.repo=repo;this.clients=clients;}

    public Lead create(LeadReq r){
        Lead l=new Lead();
        l.setName(r.name());
        l.setEmail(r.email());
        l.setPhone(r.phone());
        l.setSource(r.source());
        l.setStatus(r.status());
        return repo.save(l);
    }

    public Page<Lead> list(String status,int page,int size){
        if(status==null||status.isEmpty()) return repo.findAll(PageRequest.of(page,size));
        return repo.findByStatus(status,PageRequest.of(page,size));
    }

    public Lead update(Long id,LeadReq r){
        Lead l=repo.findById(id).orElseThrow();
        l.setName(r.name());
        l.setEmail(r.email());
        l.setPhone(r.phone());
        l.setSource(r.source());
        l.setStatus(r.status());
        return repo.save(l);
    }

    public Client convert(Long id){
        Lead l=repo.findById(id).orElseThrow();
        Client c=new Client();
        c.setName(l.getName());
        c.setEmail(l.getEmail());
        c.setPhone(l.getPhone());
        c=clients.save(c);
        l.setStatus("CONVERTED");
        repo.save(l);
        return c;
    }
}
