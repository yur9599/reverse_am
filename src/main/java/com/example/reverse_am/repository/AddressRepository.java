package com.example.reverse_am.repository;

import com.example.reverse_am.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findAddressByCountryAndCity(String country, String city);

}
