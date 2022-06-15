package com.example.reverse_am.controller;

import com.example.reverse_am.dto.AddressDTO;
import com.example.reverse_am.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        Assert.notNull(addressDTO,"Address is null ");
        AddressDTO address = addressService.createAddress(addressDTO);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id")Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().body("The address is deleted ");
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id")Long id,@Valid @RequestBody AddressDTO addressDTO){
        Assert.notNull(addressDTO,"Address is null ");
        AddressDTO address = addressService.updateAddress(id,addressDTO);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(addressService.getAddressById(id));
    }

}
