package com.example.reverse_am.mappers;

import com.example.reverse_am.dto.BagDTO;
import com.example.reverse_am.entities.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BagMapper {

    @Autowired
    private UserMapper userMapper;

    public Bag toBag(BagDTO bagDTO){
        return new Bag(this.userMapper.toUser(bagDTO.getUser()));
    }

    public BagDTO toBagDTO(Bag bag){
        return new BagDTO(this.userMapper.toUserDTO(bag.getUser()));
    }

}
