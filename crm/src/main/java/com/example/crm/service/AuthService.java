package com.example.crm.service;

import com.example.crm.dto.RegisterReq;
import com.example.crm.entity.*;
import com.example.crm.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepo repo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional
    public User register(RegisterReq r) {
        User u = new User();
        u.setUsername(r.username());
        u.setPasswordHash(encoder.encode(r.password()));
        u.setRole(Role.valueOf(r.role()));
        return repo.save(u);
    }
}
