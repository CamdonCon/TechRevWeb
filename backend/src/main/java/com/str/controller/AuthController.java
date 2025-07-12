package com.str.controller;

import com.str.model.User;
import com.str.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository users; private final PasswordEncoder encoder;
    public AuthController(UserRepository users, PasswordEncoder encoder) { this.users = users; this.encoder = encoder; }

    @PostMapping("/register")
    public void register(@RequestBody User incoming) {
        incoming.setPasswordHash(encoder.encode(incoming.getPasswordHash()));
        users.save(incoming);
    }
}