package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crm.entity.Client;
import com.example.crm.repository.ClientRepository;

@Controller
public class ClientWebController {
    private final ClientRepository clientRepository;

    public ClientWebController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }

    @PostMapping("/clients/add")
    public String addClient(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam String company) {
        Client c = new Client();
        c.setName(name);
        c.setEmail(email);
        c.setPhone(phone);
        c.setCompany(company);
        clientRepository.save(c);
        return "redirect:/clients";
    }
}
