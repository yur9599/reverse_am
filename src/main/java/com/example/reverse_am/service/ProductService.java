package com.example.reverse_am.service;

import com.example.reverse_am.dto.productDTO.AdminProductDTO;
import com.example.reverse_am.dto.productDTO.UserProductDTO;
import com.example.reverse_am.dto.productDTO.WorkerProductDTO;
import com.example.reverse_am.entities.Category;
import com.example.reverse_am.entities.Product;
import com.example.reverse_am.entities.User;
import com.example.reverse_am.exceptions.NoAccessException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.ProductMapper;
import com.example.reverse_am.repository.CategoryRepository;
import com.example.reverse_am.repository.ProductRepository;
import com.example.reverse_am.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public UserProductDTO createProduct(UserProductDTO productDTO){
        Optional<User> user = this.userRepository.findUserByEmail(productDTO.getUser().getEmail());
        if (user.isEmpty()){
            throw new ResourceNotFoundException("The user is not found ");
        }
        Optional<Category> category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
        if (category.isEmpty()){
            throw new ResourceNotFoundException("The category is not found ");
        }
        Product product = this.productMapper.toProduct(productDTO);
        product.setUser(user.get());
        product.setCategory(category.get());
        return this.productMapper.toUserProductDTO(this.productRepository.save(product));
    }

    public UserProductDTO updateProductUser(Long id, UserProductDTO productDTO){
        Optional<User> user = this.userRepository.findUserByEmail(productDTO.getUser().getEmail());
        if (user.isEmpty()){
            throw new ResourceNotFoundException("The user is not found ");
        }
        Optional<Category> category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
        if (category.isEmpty()){
            throw new ResourceNotFoundException("The category is not found ");
        }
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (product.getInWareHouse()){
            throw new NoAccessException("You no longer have access to update ");
        }
        product.setName(productDTO.getName());
        product.setCategory(category.get());
        product.setUser(user.get());
        product.setDescription(productDTO.getDescription()==null?"Not defined":productDTO.getDescription());
        product.setCondition(productDTO.getCondition());
        return this.productMapper.toUserProductDTO(this.productRepository.save(product));
    }

    public WorkerProductDTO updateProductWorker(Long id, WorkerProductDTO productDTO){
        String category1 = productDTO.getCategory().getCategory();
        Optional<Category> category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
        if (category1!=null) {
            if (category.isEmpty()) {
                throw new ResourceNotFoundException("The category is not found ");
            }
        }
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (product.getBag() == null && product.getVerification()){
            throw new NoAccessException("You no longer have access to update ");
        }
        product.setName(productDTO.getName()==null?product.getName():productDTO.getName());
        product.getCategory().setCategory(category1==null?product.getCategory().getCategory():category1);
        product.setInWareHouse(productDTO.getInWareHouse());
        product.setDescription(productDTO.getDescription()==null?product.getDescription():
                productDTO.getDescription());
        product.setCondition(productDTO.getCondition()==null?product.getCondition():
                productDTO.getCondition());
        return this.productMapper.toWorkerProductDTO(this.productRepository.save(product));
    }

    public AdminProductDTO updateProductAdmin(Long id, AdminProductDTO productDTO){
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (!product.getVerification()) {
            Long coin = product.getUser().getRevCoin();
            product.getUser().setRevCoin(coin+productDTO.getRevCoin());
            this.userRepository.save(product.getUser());
            product.setVerification(productDTO.getVerification());
        }
        product.setRevCoin(productDTO.getRevCoin());
        return this.productMapper.toAdminProductDTO(this.productRepository.save(product));
    }

    public UserProductDTO getProductById(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        return this.productMapper.toUserProductDTO(product.get());
    }

    public void deleteProduct(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        this.productRepository.delete(product.get());
    }

}
