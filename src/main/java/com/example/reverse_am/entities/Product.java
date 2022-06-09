package com.example.reverse_am.entities;

import com.example.reverse_am.entities.enums.Condition;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 50,nullable = false)
    private String name;
    @Column(name = "descrip_tion")
    private String description;
    @Column(name = "condi_tion",length = 15,nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Column(name = "verification")
    private Boolean verification;
    @Column(name = "rev_coin",nullable = false)
    private Long revCoin;
    @Column(name = "in_ware_house",nullable = false)
    private Boolean inWareHouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "fk_product_user"))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false,foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bag_id",foreignKey = @ForeignKey(name = "fk_product_bag"))
    private Bag bag;

    public Product() {
    }

    public Product(String name, String description, Condition condition,
                   Boolean verification, Long revCoin,Boolean inWareHouse, User user, Category category) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.verification = verification;
        this.revCoin = revCoin;
        this.inWareHouse = inWareHouse;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }


    public Boolean getInWareHouse() {
        return inWareHouse;
    }

    public void setInWareHouse(Boolean inWareHouse) {
        this.inWareHouse = inWareHouse;
    }

    public Long getId() {
        return id;
    }

}
