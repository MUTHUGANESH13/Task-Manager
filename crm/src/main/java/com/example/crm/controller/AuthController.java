package com.example.crm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.dto.RegisterReq;
import com.example.crm.entity.User;
import com.example.crm.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService svc;
    public AuthController(AuthService svc){this.svc=svc;}

    @PostMapping("/register")
    public User register(@RequestBody RegisterReq r){ return svc.register(r); }

    @GetMapping("/me")
    public Object me(Authentication a){ return a==null?null:a.getPrincipal(); }
}
