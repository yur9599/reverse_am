package com.example.reverse_am.repository;

import com.example.reverse_am.entities.Category;
import com.example.reverse_am.entities.Product;
import com.example.reverse_am.entities.enums.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByInWareHouseFalseAndBagIsNull();

    List<Product> findByVerificationFalseAndInWareHouseTrue();

    List<Product> findByVerificationTrue();

    @Query("select p from Product p " +
            "where (:name is null or p.name=:name) " +
            "and (:category is null or p.category=:category) " +
            "and (:condition is null or p.condition=:condition) " +
            "and (:minRevCoin is null or p.revCoin>=:minRevCoin) " +
            "and (:maxRevCoin is null or p.revCoin<=:maxRevCoin) " +
            "and p.verification= true ")
    Optional<List<Product>> dynamicFinder(@Param("name") String name, @Param("category") Category category,
                                          @Param("condition") Condition condition, @Param("minRevCoin") Long minRevCoin,
                                          @Param("maxRevCoin") Long maxRevCoin);

    @Query("select p from Product p " +
            "where (:category is null or p.category=:category) " +
            "and (:condition is null or p.condition=:condition) " +
            "and p.inWareHouse= true " +
            "and p.verification= false ")
    Optional<List<Product>> dynamicFinder(@Param("category") Category category, @Param("condition") Condition condition);

    @Query("select p from Product p " +
            "where (:category is null or p.category=:category) " +
            "and p.inWareHouse= false ")
    Optional<List<Product>> dynamicFinder(@Param("category") Category category);

}
