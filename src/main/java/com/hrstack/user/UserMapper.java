package com.hrstack.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(RegisterUserRequest request){
        return User.builder()
                .companyName(request.getCompanyName())
                .workspaceUrl(request.getWorkspaceUrl())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();
    }
}
