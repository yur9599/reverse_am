package com.example.reverse_am.dto.productDTO;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.entities.enums.Condition;

public class AdminSearchProductDTO  {

    private Condition condition;
    private CategoryDTO category;

    public AdminSearchProductDTO() {
    }

    public AdminSearchProductDTO(Condition condition, CategoryDTO category) {
        this.condition = condition;
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
