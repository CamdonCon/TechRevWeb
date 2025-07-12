package com.str.controller;

import com.str.model.*;
import com.str.repository.*;

import ch.qos.logback.core.model.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class QuoteController {

    private final QuoteRepository quotes;
    private final UserRepository users;

    public QuoteController(QuoteRepository quotes, UserRepository users) {
        this.quotes = quotes;
        this.users = users;
    }

    @GetMapping("/services")
    public String servicesPage(Model model) {
        return "services";
    }

    @PostMapping("/quote/submit")
    public String submitQuote(@RequestParam String deviceType,
                            @RequestParam String brand,
                            @RequestParam String model,
                            @RequestParam String issue,
                            RedirectAttributes redirectAttributes) {

        // Use a dummy user with ID 1 (ensure this exists in the DB)
        User dummyUser = users.findById(1L).orElseThrow();

        // Create and populate the QuoteRequest object
        QuoteRequest quote = new QuoteRequest();
        quote.setDeviceType(deviceType);
        quote.setBrand(brand);
        quote.setModel(model);
        quote.setIssue(issue);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setStatus("NEW");
        quote.setUser(dummyUser); // set user AFTER creating quote

        // Save to DB
        quotes.save(quote);

        redirectAttributes.addFlashAttribute("success", "Quote request submitted!");
        return "redirect:/services";
    }
}