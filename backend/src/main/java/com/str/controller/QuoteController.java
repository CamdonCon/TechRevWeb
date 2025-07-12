package com.str.controller;

import com.str.model.*;
import com.str.repository.*;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.*;

@RestController @RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteRepository quotes; private final UserRepository users;
    public QuoteController(QuoteRepository quotes, UserRepository users) { this.quotes = quotes; this.users = users; }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public QuoteRequest create(@RequestParam("description") String desc,
                               @RequestParam("image") MultipartFile file,
                               @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        Path uploadDir = Paths.get("uploads"); Files.createDirectories(uploadDir);
        String filename = UUID.randomUUID()+"-"+file.getOriginalFilename();
        Path target = uploadDir.resolve(filename);
        file.transferTo(target);

        User user = users.findByUsername(userDetails.getUsername()).orElseThrow();
        QuoteRequest qr = new QuoteRequest(null, user, desc, target.toString(), "NEW", null, null);
        return quotes.save(qr);
    }

    @GetMapping
    public List<QuoteRequest> myQuotes(@AuthenticationPrincipal UserDetails userDetails) {
        User user = users.findByUsername(userDetails.getUsername()).orElseThrow();
        return quotes.findByUser(user);
    }
}