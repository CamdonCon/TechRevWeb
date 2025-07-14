package com.str.controller;

import com.str.model.*;
import com.str.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class QuoteController {

    private final QuoteRepository quotes;
    private final UserRepository users;

    public QuoteController(QuoteRepository quotes, UserRepository users) {
        this.quotes = quotes;
        this.users = users;
    }

    @GetMapping("/services")
    public String servicesPage(org.springframework.ui.Model model) {
        return "services";
    }

    @PostMapping(value = "/quote/submit", consumes = {"multipart/form-data"})
    public String submitQuote(
            @RequestParam String deviceType,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam String issue,
            @RequestParam("image") MultipartFile imageFile,
            RedirectAttributes redirectAttributes) {

        User dummyUser = users.findById(1L).orElseThrow();

        String imagePath = null;
        if (!imageFile.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(filename);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePath = "/" + uploadDir + filename;
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload image.");
                return "redirect:/services";
            }
        }

        QuoteRequest quote = new QuoteRequest();
        quote.setDeviceType(deviceType);
        quote.setBrand(brand);
        quote.setModel(model);
        quote.setIssue(issue);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setStatus("NEW");
        quote.setUser(dummyUser);
        quote.setImagePath(imagePath);

        quotes.save(quote);
        redirectAttributes.addFlashAttribute("success", "Quote request submitted!");
        return "redirect:/services";
    }
}