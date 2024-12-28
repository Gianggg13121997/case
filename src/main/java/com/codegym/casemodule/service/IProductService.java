package com.codegym.casemodule.service;

import com.codegym.casemodule.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGenerateService<Product>{
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByNameContaining(Pageable pageable, String name);
}
