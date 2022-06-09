package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getCategory());
    }

    public CategoryDTO toCategoryDTO(Category category){
        return new CategoryDTO(category.getCategory());
    }

}
