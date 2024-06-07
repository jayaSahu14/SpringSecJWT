package com.codewithproj.security.service;

import com.codewithproj.security.dto.SignUpRequest;
import com.codewithproj.security.entity.User;

public interface AuthenticationService {
    public User SignUp(SignUpRequest signUpRequest);
}
