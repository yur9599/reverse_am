package com.example.reverse_am.dto.productDTO;

import com.example.reverse_am.dto.CategoryDTO;
import com.example.reverse_am.entities.enums.Condition;

import javax.validation.constraints.Min;

public class UserSearchProductDTO {

    private String name;
    private CategoryDTO category;
    private Condition condition;
    @Min(value = 0)
    private Long minRevCoin;
    @Min(value = 0)
    private Long maxRevCoin;

    public UserSearchProductDTO() {
    }

    public UserSearchProductDTO(String name, CategoryDTO category, Condition condition, Long minRevCoin, Long maxRevCoin) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.minRevCoin = minRevCoin;
        this.maxRevCoin = maxRevCoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Long getMinRevCoin() {
        return minRevCoin;
    }

    public void setMinRevCoin(Long minRevCoin) {
        this.minRevCoin = minRevCoin;
    }

    public Long getMaxRevCoin() {
        return maxRevCoin;
    }

    public void setMaxRevCoin(Long maxRevCoin) {
        this.maxRevCoin = maxRevCoin;
    }

}
