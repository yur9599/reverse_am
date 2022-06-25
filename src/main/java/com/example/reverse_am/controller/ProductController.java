package com.example.reverse_am.controller;

import com.example.reverse_am.configuration.AppUser;
import com.example.reverse_am.configuration.LoggedInUser;
import com.example.reverse_am.dto.productDTO.*;
import com.example.reverse_am.entities.enums.Condition;
import com.example.reverse_am.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(this.productService.getProductById(id));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> getProductsAdmin(){
        return ResponseEntity.ok().body(this.productService.getProductsAdmin());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> getProductsUser(){
        return ResponseEntity.ok().body(this.productService.getProductsUser());
    }

    @GetMapping("/worker")
    @PreAuthorize("hasRole('ROLE_worker')")
    public ResponseEntity<?> getProductsWorker(){
        return ResponseEntity.ok().body(this.productService.getProductsWorker());
    }

    @GetMapping("user/search/")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> searchProductsUser(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String category,
                                                @RequestParam(required = false) Condition condition,
                                                @RequestParam(required = false) Long minRevCoin,
                                                @RequestParam(required = false) Long maxRevCoin){
        return ResponseEntity.ok().body(this.productService.searchProductUser(name, category, condition, minRevCoin,
                maxRevCoin));
    }

    @GetMapping("/worker/search/")
    @PreAuthorize("hasRole('ROLE_worker')")
    public ResponseEntity<?> searchProductsWorker(@RequestParam(required = false) String category){
        return ResponseEntity.ok().body(this.productService.searchProductWorker(category));
    }

    @GetMapping("/admin/search/")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> searchProductsAdmin(@RequestParam(required = false) String category,
                                                 @RequestParam(required = false) Condition condition){
        return ResponseEntity.ok().body(this.productService.searchProductAdmin(condition, category));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody UserProductDTO product, @LoggedInUser AppUser appUser){
        Assert.notNull(product,"Product is null ");
        Assert.notNull(product.getCategory(),"Category is null ");
        UserProductDTO product1 = this.productService.createProduct(appUser.getUser(), product);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody UserProductDTO product){
        Assert.notNull(product,"Product is null ");
        Assert.notNull(product.getCategory(),"Category is null ");
        UserProductDTO product1 = this.productService.updateProductUser(id, product);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/worker/{id}")
    @PreAuthorize("hasRole('ROLE_worker')")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody WorkerProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        WorkerProductDTO product = this.productService.updateProductWorker(id,productDTO);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @Valid @RequestBody AdminProductDTO productDTO){
        Assert.notNull(productDTO,"Product is null ");
        AdminProductDTO product = this.productService.updateProductAdmin(id,productDTO);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<?> deleteProductUser(@PathVariable("id")Long id){
        this.productService.deleteProductUser(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

    @DeleteMapping("/worker/{id}")
    @PreAuthorize("hasRole('ROLE_worker')")
    public ResponseEntity<?> deleteProductWorker(@PathVariable("id")Long id){
        this.productService.deleteProductWorker(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> deleteProductAdmin(@PathVariable("id")Long id){
        this.productService.deleteProductAdmin(id);
        return ResponseEntity.ok().body("Product is deleted ");
    }

}
