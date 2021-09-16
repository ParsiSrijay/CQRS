package com.appsdeveloperblog.estore.ProductsService.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

@RestController
@RequestMapping("/products")  // https://localhost:8080/products
public class ProductsController {

    @Autowired
    private Environment env;

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel){
        return "Http Post is handled" + createProductRestModel.getTitle();
    }

    @GetMapping
    public String getProduct(){
        return "Http Get is handled " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct(){
        return "Http Put is handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "Http delete handled";
    }
}
