package com.example.reverse_am.service;

import com.example.reverse_am.dto.BagDTO;
import com.example.reverse_am.entities.Bag;
import com.example.reverse_am.entities.User;
import com.example.reverse_am.exceptions.DuplicateResourceException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.BagMapper;
import com.example.reverse_am.mappers.UserMapper;
import com.example.reverse_am.repository.BagRepository;
import com.example.reverse_am.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BagService {

    private final BagRepository bagRepository;
    private final BagMapper bagMapper;
    private final UserRepository userRepository;

    @Autowired
    public BagService(BagRepository bagRepository, BagMapper bagMapper, UserRepository userRepository, UserMapper userMapper) {
        this.bagRepository = bagRepository;
        this.bagMapper = bagMapper;
        this.userRepository = userRepository;
    }

    public BagDTO createBag(BagDTO bagDTO){
        Optional<User> user = this.userRepository.findUserByEmail(bagDTO.getUser().getEmail());
        if (user.isEmpty()){
            throw new ResourceNotFoundException("The user is not found ");
        }
        Optional<Bag> bag = this.bagRepository.findBagByUser(user.get());
        if (bag.isPresent()){
            throw new DuplicateResourceException("There is already bag with such user ");
        }
        Bag bag1 = this.bagMapper.toBag(bagDTO);
        bag1.setUser(user.get());
        Bag bag2 = this.bagRepository.save(bag1);
        return this.bagMapper.toBagDTO(bag2);
    }

    public BagDTO updateBag(Long id ,BagDTO bagDTO){
        Optional<Bag> bag = this.bagRepository.findById(id);
        if (bag.isEmpty()){
            throw new ResourceNotFoundException("The bag is not found ");
        }
        Optional<User> user = this.userRepository.findUserByEmail(bagDTO.getUser().getEmail());
        if (user.isEmpty()){
            throw new ResourceNotFoundException("The user is not found ");
        }
        Optional<Bag> bag2 = this.bagRepository.findBagByUser(user.get());
        if (bag2.isPresent()){
            throw new DuplicateResourceException("There is already bag with such user ");
        }
        bag.get().setUser(user.get());
        Bag bag1 = this.bagRepository.save(bag.get());
        return this.bagMapper.toBagDTO(bag1);
    }

    public void deleteBag(Long id){
        Optional<Bag> bag = this.bagRepository.findById(id);
        if (bag.isEmpty()){
            throw new ResourceNotFoundException("The bag is not found ");
        }
        this.bagRepository.delete(bag.get());
    }

    public BagDTO getBagById(Long id){
        Optional<Bag> bag = this.bagRepository.findById(id);
        if (bag.isEmpty()){
            throw new ResourceNotFoundException("The bag is not found ");
        }
        return this.bagMapper.toBagDTO(bag.get());
    }

}
