package com.example.reverse_am.service;

import com.example.reverse_am.dto.BagDTO;
import com.example.reverse_am.dto.UserDTO;
import com.example.reverse_am.entities.Address;
import com.example.reverse_am.entities.Bag;
import com.example.reverse_am.entities.Product;
import com.example.reverse_am.entities.User;
import com.example.reverse_am.exceptions.DuplicateResourceException;
import com.example.reverse_am.exceptions.InsufficientFundsException;
import com.example.reverse_am.exceptions.NoAccessException;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.mappers.AddressMapper;
import com.example.reverse_am.mappers.UserMapper;
import com.example.reverse_am.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final BagService bagService;
    private final BagRepository bagRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, AddressRepository addressRepository,
                       AddressMapper addressMapper, BagService bagService, BagRepository bagRepository,
                       ProductRepository productRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.bagService = bagService;
        this.bagRepository = bagRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserDTO createUser(UserDTO userDTO){
        if (this.userRepository.existsByEmail(userDTO.getEmail())){
            throw new DuplicateResourceException("There is already user with such email ");
        }
        if (this.userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())){
            throw new DuplicateResourceException("There is already user with such phone number ");
        }
        Address addressDb1;
        Address address = addressMapper.toAddress(userDTO.getAddress());
        Optional<Address> addressDb = this.addressRepository.
                findAddressByCountryAndCity(address.getCountry(), address.getCity());
        if (addressDb.isEmpty()){
            addressDb1 = this.addressRepository.save(address);
        }else {
            addressDb1 = addressDb.get();
        }
        User user = userMapper.toUser(userDTO);
        user.setRole(this.roleRepository.findRoleByRole(user.getRole().getRole()).get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAddress(addressDb1);
        User user1 = this.userRepository.save(user);
        this.bagService.createBag(new BagDTO(this.userMapper.toUserDTO(user)));
        return this.userMapper.toUserDTO(user1);
    }

    public UserDTO updateUser(User user, UserDTO userDTO){
        Address addressDb1 ;
        Address address = addressMapper.toAddress(userDTO.getAddress());
        Optional<Address> addressDb = this.addressRepository.
                findAddressByCountryAndCity(address.getCountry(), address.getCity());
        if (addressDb.isEmpty()){
            addressDb1 = this.addressRepository.save(address);
        }else {
            addressDb1 = addressDb.get();
        }
        user.setName(userDTO.getName());
        user.setSureName(userDTO.getSureName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAddress(addressDb1);
        return this.userMapper.toUserDTO(this.userRepository.save(user));
    }

    public void buyProduct(User user, Long pId){
//        Optional<User> userDB = this.userRepository.findById(uId);
//        if (userDB.isEmpty()){
//            throw new ResourceNotFoundException("The user is not found ");
//        }
//        User user = userDB.get();
        Optional<Bag> bagDB = this.bagRepository.findBagByUser(user);
        if (bagDB.isEmpty()){
            throw new ResourceNotFoundException("The bag is not found ");
        }
        Optional<Product> productDB = this.productRepository.findById(pId);
        if (productDB.isEmpty()){
            throw new ResourceNotFoundException("The product is not found ");
        }
        Product product = productDB.get();
        if (product.getUser().getId().equals(user.getId())){
            throw new NoAccessException("You can`t buy your posted product ");
        }
        if (product.getBag() == null && product.getVerification()){
            if (product.getRevCoin() <= user.getRevCoin()) {
                user.setRevCoin(user.getRevCoin()-product.getRevCoin());
                product.setBag(bagDB.get());
                this.productRepository.save(product);
                this.userRepository.save(user);
            }
            else {
                throw new InsufficientFundsException("There is not enough money in the account");
            }
        }
        else {
            throw new NoAccessException("The product is not available ");
        }
    }

    public void deleteUser(User user){
//        Optional<User> user = this.userRepository.findById(id);
//        if (user.isEmpty()){
//            throw new ResourceNotFoundException("The user is not found ");
//        }
        if (user.getRole().getRole().equals("admin") || user.getRole().getRole().equals("worker")){
            throw new NoAccessException("You can`t delete admin`s page ");
        }
        this.bagRepository.delete( this.bagRepository.findBagByUser(user).get());
        this.userRepository.delete(user);
    }

    public UserDTO getUserById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("The user is not found ");
        }
        return this.userMapper.toUserDTO(user.get());
    }

}
