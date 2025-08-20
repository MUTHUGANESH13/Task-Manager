package com.example.crm.controller;

import com.example.crm.entity.Lead;
import com.example.crm.repository.LeadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeadWebController {
    private final LeadRepository leadRepository;

    public LeadWebController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @GetMapping("/leads")
    public String leads(Model model) {
        model.addAttribute("leads", leadRepository.findAll());
        return "leads";
    }

    @PostMapping("/leads/add")
    public String addLead(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phone,
                          @RequestParam String source) {
        Lead l = new Lead();
        l.setName(name);
        l.setEmail(email);
        l.setPhone(phone);
        l.setSource(source);
        l.setStatus("New");
        leadRepository.save(l);
        return "redirect:/leads";
    }
}
