package com.example.crm.controller;

import com.example.crm.dto.ClientReq;
import com.example.crm.entity.Client;
import com.example.crm.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService svc;
    public ClientController(ClientService svc){this.svc=svc;}

    @PostMapping
    public Client create(@RequestBody ClientReq r){ return svc.create(r); }

    @GetMapping
    public Page<Client> list(@RequestParam(required=false) String search,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){
        return svc.search(search,page,size);
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable Long id){ return svc.get(id); }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id,@RequestBody ClientReq r){ return svc.update(id,r); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ svc.delete(id); }
}
