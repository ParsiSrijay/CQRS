package com.appsdeveloperblog.estore.ProductsService.command.rest;

import com.appsdeveloperblog.estore.ProductsService.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import java.util.UUID;

@RestController
@RequestMapping("/products")  // https://localhost:8080/products
public class ProductsCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        String returnvalue;
        try {
            returnvalue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnvalue = ex.getLocalizedMessage();
        }
        return returnvalue;
    }
}