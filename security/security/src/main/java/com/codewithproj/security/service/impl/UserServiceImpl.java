package com.codewithproj.security.service.impl;


import com.codewithproj.security.repository.UserRepo;
import com.codewithproj.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private  UserRepo userRepository;

   public UserDetailsService userDetailsService(){
       return new UserDetailsService() {
           @Override
           public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return userRepository.findByEmail(username)
                       .orElseThrow(()-> new UsernameNotFoundException("user not found"));
           }
       };
   }
}

