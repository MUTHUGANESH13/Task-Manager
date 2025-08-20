package com.example.crm.service;

import com.example.crm.dto.ClientReq;
import com.example.crm.entity.Client;
import com.example.crm.repo.ClientRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepo repo;
    public ClientService(ClientRepo repo){this.repo=repo;}

    public Client create(ClientReq req){
        Client c=new Client();
        c.setName(req.name());
        c.setEmail(req.email());
        c.setPhone(req.phone());
        c.setCompany(req.company());
        return repo.save(c);
    }

    public Page<Client> search(String q,int page,int size){
        return repo.search(q, PageRequest.of(page,size));
    }

    public Client get(Long id){ return repo.findById(id).orElseThrow(); }

    public Client update(Long id, ClientReq req){
        Client c=get(id);
        c.setName(req.name());
        c.setEmail(req.email());
        c.setPhone(req.phone());
        c.setCompany(req.company());
        return repo.save(c);
    }

    public void delete(Long id){ repo.deleteById(id); }
}
