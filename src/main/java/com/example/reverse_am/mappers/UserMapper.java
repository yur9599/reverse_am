package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.UserDTO;
import com.example.reverse_am.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private AddressMapper addressMapper;

    public User toUser(UserDTO userDto){
        return new User(userDto.getName(), userDto.getSureName(), userDto.getPhoneNumber(), userDto.getEmail(),
                userDto.getPassword(),0L,addressMapper.toAddress(userDto.getAddress()));
    }

    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getName(), user.getSureName(), user.getPhoneNumber(), user.getEmail(),
                user.getPassword(),addressMapper.toAddressDTO(user.getAddress()),user.getRevCoin());
    }

}
