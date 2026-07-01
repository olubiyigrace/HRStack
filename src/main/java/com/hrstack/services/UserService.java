package com.hrstack.services;

import com.hrstack.dto.RegisterUserRequest;

public interface UserService {
    void create(RegisterUserRequest request);
    void resendVerificationOtp(String email);
}
