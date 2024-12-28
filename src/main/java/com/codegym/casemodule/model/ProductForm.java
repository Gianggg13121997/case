package com.codegym.casemodule.model;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductForm {
    private Long id;
    private String name;
    private Double price;
    private Double quantity;
    private MultipartFile image;
    private String description;
}