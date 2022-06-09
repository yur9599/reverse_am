package com.example.reverse_am.service;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.entities.Category;
import com.example.reverse_am.exceptions.DuplicateResourceException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.CategoryMapper;
import com.example.reverse_am.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Optional<Category> category = this.categoryRepository.findByCategory(categoryDTO.getCategory());
        if (category.isPresent()){
            throw new DuplicateResourceException("There is already such category ");
        }
        this.categoryRepository.save(categoryMapper.toCategory(categoryDTO));
        return categoryDTO;
    }

    public CategoryDTO updateCategory(Long id ,CategoryDTO categoryDTO){
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new ResourceNotFoundException("The category is not found ");
        }
        category.get().setCategory(categoryDTO.getCategory());
        this.categoryRepository.save(category.get());
        return categoryDTO;
    }

    public void deleteCategory(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new ResourceNotFoundException("The category is not found ");
        }
        this.categoryRepository.delete(category.get());
    }

    public CategoryDTO getCategoryById(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new ResourceNotFoundException("The category is not found ");
        }
        return this.categoryMapper.toCategoryDTO(category.get());
    }

}
