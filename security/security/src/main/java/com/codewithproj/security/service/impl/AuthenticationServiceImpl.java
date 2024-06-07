package com.codewithproj.security.service.impl;

import com.codewithproj.security.dto.SignUpRequest;
import com.codewithproj.security.entity.Role;
import com.codewithproj.security.entity.User;
import com.codewithproj.security.repository.UserRepo;
import com.codewithproj.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;

    public User SignUp(SignUpRequest signUpRequest){
        User users = new User();

       users.setEmail(signUpRequest.geteMail());
       users.setFname(signUpRequest.getfName());
       users.setLname(signUpRequest.getlName());
       users.setRole(Role.USER);
       users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

       return userRepository.save(users);
    }

}
