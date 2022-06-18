package com.example.reverse_am.dto.productDTO;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.entities.enums.Condition;

public class UserViewProductDTO {

    private Long id;
    private String name;
    private String description;
    private Condition condition;
    private Long revCoin;
    private CategoryDTO category;

    public UserViewProductDTO() {
    }

    public UserViewProductDTO(Long id, String name, String description, Condition condition, Long revCoin, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.revCoin = revCoin;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Long getRevCoin() {
        return revCoin;
    }

    public void setRevCoin(Long revCoin) {
        this.revCoin = revCoin;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
