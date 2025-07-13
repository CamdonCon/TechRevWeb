package com.str.controller;

import com.str.model.*;
import com.str.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        return "products"; // This maps to products.html
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double price,
                            @RequestParam String imageUrl,
                            RedirectAttributes redirectAttributes) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setImageUrl(imageUrl);

        products.save(newProduct);

        redirectAttributes.addFlashAttribute("success", "Product added!");
        return "redirect:/products";
    }
}