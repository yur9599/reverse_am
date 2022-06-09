package com.example.reverse_am.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {
    @Size(min = 3,max = 45)
    @NotNull
    private String category;

    public CategoryDTO() {
    }

    public CategoryDTO(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
