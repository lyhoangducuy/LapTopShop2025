package com.latptop.flexuy.controller.ProductAmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.latptop.flexuy.service.product.ProductService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/viewProduct")
    public String getProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/view";
    }
    
}
