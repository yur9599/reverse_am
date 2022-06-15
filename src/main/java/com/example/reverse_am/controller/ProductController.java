package com.example.reverse_am.controller;

import com.example.reverse_am.dto.productDTO.*;
import com.example.reverse_am.entities.enums.Condition;
import com.example.reverse_am.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(this.productService.getProductById(id));
    }

    @GetMapping("/products/admin")
    public ResponseEntity<?> getProductsAdmin(){
        return ResponseEntity.ok().body(this.productService.getProductsAdmin());
    }

    @GetMapping("/products/user")
    public ResponseEntity<?> getProductsUser(){
        return ResponseEntity.ok().body(this.productService.getProductsUser());
    }

    @GetMapping("/products/worker")
    public ResponseEntity<?> getProductsWorker(){
        return ResponseEntity.ok().body(this.productService.getProductsWorker());
    }

    @GetMapping("/products/search/user")
    public ResponseEntity<?> searchProductsUser(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String category,
                                                @RequestParam(required = false) Condition condition,
                                                @RequestParam(required = false) Long minRevCoin,
                                                @RequestParam(required = false) Long maxRevCoin){
        return ResponseEntity.ok().body(this.productService.searchProductUser(name, category, condition, minRevCoin,
                maxRevCoin));
    }

    @GetMapping("/products/search/worker")
    public ResponseEntity<?> searchProductsWorker(@RequestParam(required = false) String category){
        return ResponseEntity.ok().body(this.productService.searchProductWorker(category));
    }

    @GetMapping("/products/search/admin")
    public ResponseEntity<?> searchProductsAdmin(@RequestParam(required = false) String category,
                                                 @RequestParam(required = false) Condition condition){
        return ResponseEntity.ok().body(this.productService.searchProductAdmin(condition, category));
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody UserProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        Assert.notNull(productDTO.getCategory(),"Category is null ");
        Assert.notNull(productDTO.getUser(),"User is null ");
        UserProductDTO product = this.productService.createProduct(productDTO);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products/user/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody UserProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        Assert.notNull(productDTO.getCategory(),"Category is null ");
        Assert.notNull(productDTO.getUser(),"User is null ");
        UserProductDTO product = this.productService.updateProductUser(id,productDTO);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products/worker/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody WorkerProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        WorkerProductDTO product = this.productService.updateProductWorker(id,productDTO);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products/admin/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody AdminProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        AdminProductDTO product = this.productService.updateProductAdmin(id,productDTO);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/products/user/{id}")
    public ResponseEntity<?> deleteProductUser(@PathVariable("id")Long id){
        this.productService.deleteProductUser(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

    @DeleteMapping("/products/worker/{id}")
    public ResponseEntity<?> deleteProductWorker(@PathVariable("id")Long id){
        this.productService.deleteProductWorker(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

    @DeleteMapping("/products/admin/{id}")
    public ResponseEntity<?> deleteProductAdmin(@PathVariable("id")Long id){
        this.productService.deleteProductAdmin(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

}
