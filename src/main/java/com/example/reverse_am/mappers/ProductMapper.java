package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.productDTO.*;
import com.example.reverse_am.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.reverse_am.entities.enums.Condition.NOT_DEFINED;

@Component
public class ProductMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductMapper(UserMapper userMapper, CategoryMapper categoryMapper) {
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    public UserProductDTO toUserProductDTO(Product product){
        return new UserProductDTO(product.getName(), product.getDescription(), product.getCondition(),
                userMapper.toUserDTO(product.getUser()), categoryMapper.toCategoryDTO(product.getCategory()));
    }

    public WorkerProductDTO toWorkerProductDTO(Product product){
        return new WorkerProductDTO(product.getName(), product.getDescription(), product.getCondition(),
                product.getInWareHouse(), categoryMapper.toCategoryDTO(product.getCategory()));
    }

    public AdminProductDTO toAdminProductDTO(Product product){
        return new AdminProductDTO(product.getVerification(),  product.getRevCoin());
    }

    public Product toProduct(UserProductDTO productDTO){
        return new Product(productDTO.getName(),
                productDTO.getDescription()==null?"Not defined":productDTO.getDescription(),
                productDTO.getCondition()==null? NOT_DEFINED:productDTO.getCondition(),
                false, 0L,false,
                userMapper.toUser(productDTO.getUser()), categoryMapper.toCategory(productDTO.getCategory()));
    }

    private UserViewProductDTO toUserViewProductDTO(Product product){
        return new UserViewProductDTO(product.getName(), product.getDescription(), product.getCondition(),
                product.getRevCoin(), this.categoryMapper.toCategoryDTO(product.getCategory()));
    }

    private WorkerViewProductDTO toWorkerViewProductDTO(Product product){
        return new WorkerViewProductDTO(product.getName(), product.getDescription(),
                this.categoryMapper.toCategoryDTO(product.getCategory()));
    }

    private AdminViewProductDTO toAdminViewProductDTO(Product product){
        return new AdminViewProductDTO(product.getName(), product.getDescription(), product.getCondition(),
                this.categoryMapper.toCategoryDTO(product.getCategory()));
    }

    public List<UserViewProductDTO> mapAllProductsForUser(List<Product> products){
        return products.stream().map(this::toUserViewProductDTO).collect(Collectors.toList());
    }

    public List<WorkerViewProductDTO> mapAllProductsForWorker(List<Product> products){
        return products.stream().map(this::toWorkerViewProductDTO).collect(Collectors.toList());
    }

    public List<AdminViewProductDTO> mapAllProductsForAdmin(List<Product> products){
        return products.stream().map(this::toAdminViewProductDTO).collect(Collectors.toList());
    }

}
