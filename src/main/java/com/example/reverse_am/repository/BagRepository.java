package com.example.reverse_am.repository;

import com.example.reverse_am.entities.Bag;
import com.example.reverse_am.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag,Long> {

    Optional<Bag> findBagByUser(User user);

}
