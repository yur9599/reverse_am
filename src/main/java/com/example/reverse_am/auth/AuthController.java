//package com.example.reverse_am.auth;
//
//import com.example.reverse_am.entities.User;
//import com.example.reverse_am.exceptions.ResourceNotFoundException;
//import com.example.reverse_am.repository.UserRepository;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder
//    //                      AuthenticationManager authenticationManager
//    ) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @GetMapping("api/auths")
//    public ResponseEntity<?> auth(@RequestBody Credentials credentials){
//        System.out.println(passwordEncoder.encode(credentials.getPassword()));
//        Optional<User> userDB = this.userRepository.findUserByEmailAndPassword(credentials.getEmail(),
//                passwordEncoder.encode(credentials.getPassword()));
//        if (userDB.isEmpty()){
//            throw new ResourceNotFoundException("User not found ");
//        }
//        String token = Jwts.builder().claim("email", userDB.get().getEmail()).
//                signWith(SignatureAlgorithm.HS512, "hola-kepasa").compact();
//        return ResponseEntity.ok().body(token);
//    }
//
//}
