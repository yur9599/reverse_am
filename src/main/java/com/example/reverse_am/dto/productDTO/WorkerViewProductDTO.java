package com.example.reverse_am.dto.productDTO;

import com.example.reverse_am.dto.CategoryDTO;

public class WorkerViewProductDTO {

    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;

    public WorkerViewProductDTO() {
    }

    public WorkerViewProductDTO(Long id, String name, String description, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
