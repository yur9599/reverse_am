package com.example.reverse_am.configuration;

import com.example.reverse_am.entities.User;
import com.example.reverse_am.exceptions.ResourceNotFoundException;
import com.example.reverse_am.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDB = this.userRepository.findUserByEmail(email);
        if (userDB.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        return new AppUser(userDB.get());
    }

}
