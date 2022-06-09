package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.productDTO.AdminProductDTO;
import com.example.reverse_am.dto.productDTO.UserProductDTO;
import com.example.reverse_am.dto.productDTO.WorkerProductDTO;
import com.example.reverse_am.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    public Product toProduct(WorkerProductDTO productDTO){
//        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getCondition(),
//                false, 0L, productDTO.getInWareHouse(), userMapper.toUser(productDTO.getUser())
//                ,categoryMapper.toCategory(productDTO.getCategory()));
//    }
//
//    public Product toProduct(AdminProductDTO productDTO){
//        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getCondition(),
//                productDTO.getVerification(), productDTO.getRevCoin(), productDTO.getInWareHouse(),
//                userMapper.toUser(productDTO.getUser()) ,categoryMapper.toCategory(productDTO.getCategory()));
//    }

}
