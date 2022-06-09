package com.example.reverse_am.controller;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id")Long id){
        CategoryDTO category = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        Assert.notNull(categoryDTO,"Category is null ");
        CategoryDTO category = this.categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id")Long id ,@Valid @RequestBody CategoryDTO categoryDTO){
        Assert.notNull(categoryDTO,"Category is null ");
        CategoryDTO category = this.categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id")Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Category is deleted ");
    }

}
