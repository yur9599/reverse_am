package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.AddressDTO;
import com.example.reverse_am.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toAddressDTO(Address address){
        return new AddressDTO(address.getCountry(), address.getCity());
    }

    public Address toAddress(AddressDTO addressDTO){
        return new Address(addressDTO.getCountry(), addressDTO.getCity());
    }

}
