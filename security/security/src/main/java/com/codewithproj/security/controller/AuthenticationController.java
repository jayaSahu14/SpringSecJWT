package com.codewithproj.security.controller;

import com.codewithproj.security.dto.SignUpRequest;
import com.codewithproj.security.entity.User;
import com.codewithproj.security.service.AuthenticationService;
import com.codewithproj.security.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.SignUp(signUpRequest));
    }

}
