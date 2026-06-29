package com.hrstack.services;

import com.hrstack.dto.requestDto.RegisterUserRequest;

public interface UserService {
    void create(RegisterUserRequest request);
}
