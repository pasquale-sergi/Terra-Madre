package dev.pasq.terra_madre.controller;

import dev.pasq.terra_madre.dto.ProductAddingDto;
import dev.pasq.terra_madre.entity.Product;
import dev.pasq.terra_madre.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")

public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/{productName}")
    public ResponseEntity<?> getProduct(@PathVariable("productName") String name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        Product product = productService.getProduct(decodedName);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No product found with name: " + decodedName);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductAddingDto request){
        try{
            Product product = productService.addProduct(request.getProductName(), request.getDescription(), request.getPrice(), request.getQuantity(), request.getCategory());
            return ResponseEntity.ok(product);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Having issue adding the product");
        }
    }
}
