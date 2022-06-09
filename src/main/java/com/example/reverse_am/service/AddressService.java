package com.example.reverse_am.service;

import com.example.reverse_am.dto.AddressDTO;
import com.example.reverse_am.entities.Address;
import com.example.reverse_am.exceptions.DuplicateResourceException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.AddressMapper;
import com.example.reverse_am.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public AddressDTO createAddress(AddressDTO addressDTO){
        Optional<Address> address = this.addressRepository.
                findAddressByCountryAndCity(addressDTO.getCountry(), addressDTO.getCity());
        if (address.isPresent()) {
            throw new DuplicateResourceException("There is already such address ");
        }
        this.addressRepository.save(addressMapper.toAddress(addressDTO));
        return addressDTO;
    }

    public void deleteAddress(Long id){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()){
            throw new ResourceNotFoundException("The address is not found ");
        }
        this.addressRepository.delete(address.get());
    }

    public AddressDTO updateAddress(Long id,AddressDTO addressDTO){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()){
            throw new ResourceNotFoundException("The address is not found ");
        }
        address.get().setCity(addressDTO.getCity());
        address.get().setCountry(addressDTO.getCountry());
        this.addressRepository.save(address.get());
        return addressDTO;
    }

    public AddressDTO getAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()){
            throw new ResourceNotFoundException("The address is not found ");
        }
        return this.addressMapper.toAddressDTO(address.get());
    }

}
