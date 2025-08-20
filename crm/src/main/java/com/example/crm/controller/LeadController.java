package com.example.crm.controller;

import com.example.crm.dto.LeadReq;
import com.example.crm.entity.*;
import com.example.crm.service.LeadService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    private final LeadService svc;
    public LeadController(LeadService svc){this.svc=svc;}

    @PostMapping
    public Lead create(@RequestBody LeadReq r){ return svc.create(r); }

    @GetMapping
    public Page<Lead> list(@RequestParam(required=false) String status,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){
        return svc.list(status,page,size);
    }

    @PutMapping("/{id}")
    public Lead update(@PathVariable Long id,@RequestBody LeadReq r){ return svc.update(id,r); }

    @PostMapping("/{id}/convert")
    public Client convert(@PathVariable Long id){ return svc.convert(id); }
}
