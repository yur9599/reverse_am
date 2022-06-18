package com.example.reverse_am.service;

import com.example.reverse_am.dto.productDTO.*;
import com.example.reverse_am.entities.Category;
import com.example.reverse_am.entities.Product;
import com.example.reverse_am.entities.User;
import com.example.reverse_am.entities.enums.Condition;
import com.example.reverse_am.exceptions.NoAccessException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.CategoryMapper;
import com.example.reverse_am.mappers.ProductMapper;
import com.example.reverse_am.repository.CategoryRepository;
import com.example.reverse_am.repository.ProductRepository;
import com.example.reverse_am.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public UserProductDTO createProduct(User user, UserProductDTO productDTO) {
        Optional<Category> category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("The category is not found ");
        }
        Product product = this.productMapper.toProduct(productDTO);
        product.setUser(user);
        product.setCategory(category.get());
        return this.productMapper.toUserProductDTO(this.productRepository.save(product));
    }

    public List<UserViewProductDTO> getProductsUser() {
        List<Product> userList = productRepository.findByVerificationTrue();
        return productMapper.mapAllProductsForUser(userList);
    }

    public List<WorkerViewProductDTO> getProductsWorker() {
        List<Product> workerList = productRepository.findByInWareHouseFalseAndBagIsNull();
        return productMapper.mapAllProductsForWorker(workerList);
    }

    public List<AdminViewProductDTO> getProductsAdmin() {
        List<Product> adminList = productRepository.findByVerificationFalseAndInWareHouseTrue();
        return productMapper.mapAllProductsForAdmin(adminList);
    }

    public UserProductDTO updateProductUser(Long id, UserProductDTO productDTO) {
        Optional<Category> category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("The category is not found ");
        }
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (product.getInWareHouse()) {
            throw new NoAccessException("You no longer have access to update ");
        }
        product.setName(productDTO.getName());
        product.setCategory(category.get());
        product.setDescription(productDTO.getDescription() == null ? "Not defined" : productDTO.getDescription());
        product.setCondition(productDTO.getCondition());
        return this.productMapper.toUserProductDTO(this.productRepository.save(product));
    }

    public WorkerProductDTO updateProductWorker(Long id, WorkerProductDTO productDTO) {
        Optional<Category> category;
        Category category1 = null;
        if (productDTO.getCategory() != null) {
            category = this.categoryRepository.findByCategory(productDTO.getCategory().getCategory());
            if (category.isEmpty()) {
                throw new ResourceNotFoundException("The category is not found ");
            }
            category1 = category.get();
        }
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (product.getBag() == null && product.getVerification()) {
            throw new NoAccessException("You no longer have access to update ");
        }
        product.setName(productDTO.getName() == null ? product.getName() : productDTO.getName());
        product.setCategory(category1 == null? product.getCategory() : category1);
        product.setInWareHouse(productDTO.getInWareHouse());
        product.setDescription(productDTO.getDescription() == null ? product.getDescription() :
                productDTO.getDescription());
        product.setCondition(productDTO.getCondition() == null ? product.getCondition() :
                productDTO.getCondition());
        return this.productMapper.toWorkerProductDTO(this.productRepository.save(product));
    }

    public AdminProductDTO updateProductAdmin(Long id, AdminProductDTO productDTO) {
        Optional<Product> productDB = this.productRepository.findById(id);
        if (productDB.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (!product.getVerification()) {
            Long coin = product.getUser().getRevCoin();
            product.getUser().setRevCoin(coin + productDTO.getRevCoin());
            this.userRepository.save(product.getUser());
            product.setVerification(productDTO.getVerification());
        }
        product.setRevCoin(productDTO.getRevCoin());
        return this.productMapper.toAdminProductDTO(this.productRepository.save(product));
    }

    public UserProductDTO getProductById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        return this.productMapper.toUserProductDTO(product.get());
    }

    public List<UserViewProductDTO> searchProductUser(String name, String category, Condition condition,
                                                      Long minRevCoin, Long maxRevCoin) {
        Optional<List<Product>> products;
        if (category!=null) {
            Optional<Category> categoryDB = this.categoryRepository.findByCategory(category);
            if (categoryDB.isEmpty()) {
                throw new ResourceNotFoundException("The category is not found ");
            }
            products = this.productRepository.dynamicFinder(name, categoryDB.get(),
                    condition, minRevCoin, maxRevCoin);
        }else {
            products = this.productRepository.dynamicFinder(name, null, condition, minRevCoin, maxRevCoin);
        }
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products is not found ");
        }
        return this.productMapper.mapAllProductsForUser(products.get());
    }

    public List<WorkerViewProductDTO> searchProductWorker(String category){
        Optional<List<Product>> products;
        if (category!=null) {
            Optional<Category> categoryDB = this.categoryRepository.findByCategory(category);
            if (categoryDB.isEmpty()) {
                throw new ResourceNotFoundException("The category is not found ");
            }
            products = this.productRepository.dynamicFinder(categoryDB.get());
        }else {
            products = this.productRepository.dynamicFinder(null);
        }
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products is not found ");
        }
        return this.productMapper.mapAllProductsForWorker(products.get());
    }

    public List<AdminViewProductDTO> searchProductAdmin(Condition condition, String category){
        Optional<List<Product>> products;
        if (category!=null) {
            Optional<Category> categoryDB = this.categoryRepository.findByCategory(category);
            if (categoryDB.isEmpty()) {
                throw new ResourceNotFoundException("The category is not found ");
            }
            products = this.productRepository.dynamicFinder(categoryDB.get(),condition);
        }else {
            products = this.productRepository.dynamicFinder(null, condition);
        }
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products is not found ");
        }
        return this.productMapper.mapAllProductsForAdmin(products.get());
    }

    public void deleteProductUser(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        if (product.get().getVerification()){
            throw new NoAccessException("You can`t take it back ");
        }
        this.productRepository.delete(product.get());
    }

    public void deleteProductWorker(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        if (!product.get().getVerification() && product.get().getBag() == null){
            this.productRepository.delete(product.get());
        }else {
            throw new NoAccessException("You can`t delete it ");
        }
    }

    public void deleteProductAdmin(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("The product is not found ");
        }
        if (!product.get().getInWareHouse() && product.get().getBag() != null){
            this.productRepository.delete(product.get());
        }else {
            throw new NoAccessException("You can`t delete it ");
        }
    }

}
