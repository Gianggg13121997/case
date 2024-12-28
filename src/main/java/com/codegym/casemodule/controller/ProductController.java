package com.codegym.casemodule.controller;

import com.codegym.casemodule.action.GetUsername;
import com.codegym.casemodule.model.AppUser;
import com.codegym.casemodule.model.Product;
import com.codegym.casemodule.model.ProductForm;
import com.codegym.casemodule.service.IAppUserService;
import com.codegym.casemodule.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("productForm") ProductForm productForm) {
        Product product = new Product(); // Tạo mới một đối tượng Product
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setDescription(productForm.getDescription());

        // Lưu file ảnh
        if (productForm.getImage() != null && !productForm.getImage().isEmpty()) {
            try {
                String uploadDir = "uploads/"; // Thư mục lưu ảnh
                String fileName = productForm.getImage().getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.createDirectories(filePath.getParent()); // Tạo thư mục nếu chưa tồn tại
                Files.write(filePath, productForm.getImage().getBytes()); // Lưu file
                product.setImageUrl("/" + uploadDir + fileName); // Lưu đường dẫn ảnh
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.save(product);
        return new ModelAndView("/products");
    }
//
//    @GetMapping("")
//    public ModelAndView listProducts() {
//        Iterable<Product> products = productService.findAll();
//        ModelAndView modelAndView = new ModelAndView("product/list");
//        modelAndView.addObject("products", products);
//        return modelAndView;
//
//    }

    @GetMapping("")
    public ModelAndView listProducts(@RequestParam("search") Optional<String> search, @PageableDefault(value = 3) Pageable pageable) {
        Page<Product> products;
        ModelAndView modelAndView;
        if (search.isPresent()) {
            products = productService.findAllByNameContaining(pageable, search.get());
            modelAndView = new ModelAndView("/product/list");
            modelAndView.addObject("products", products);
            modelAndView.addObject("search", search.get());
        } else {
            products = productService.findAll(pageable);
            modelAndView = new ModelAndView("/product/list");
            modelAndView.addObject("products", products);
        }
        return modelAndView;
    }


    @GetMapping("update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("product/update");
            modelAndView.addObject("product", product.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error_404");
        }

    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/update");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    // Sử dụng GetUsername để lấy username
    @ModelAttribute("currentUsername")
    public String getCurrentUsername() {
        GetUsername getUsername = new GetUsername();
        return getUsername.getUsername();
    }






}
