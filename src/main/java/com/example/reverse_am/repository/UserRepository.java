package com.example.reverse_am.repository;

import com.example.reverse_am.entities.Role;
import com.example.reverse_am.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNUmber);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<List<User>> findUserByRole(Role role);

}
