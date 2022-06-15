package com.example.reverse_am.dto.productDTO;

import com.example.reverse_am.dto.CategoryDTO;

public class WorkerSearchProductDTO {

    private CategoryDTO category;

    public WorkerSearchProductDTO() {
    }

    public WorkerSearchProductDTO(CategoryDTO category) {
        this.category = category;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
