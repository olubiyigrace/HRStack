package com.hrstack.mappers;

import com.hrstack.dto.requestDto.RegisterAdminRequest;
import com.hrstack.entities.User;
import com.hrstack.properties.WorkspaceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {
    private final WorkspaceProperties workspaceProperties;

    public User toEntity(RegisterAdminRequest request) {
        return User.builder()
                .companyName(request.getCompanyName())
                .workspaceUrl(workspaceProperties.getBaseUrl() + request.getWorkspaceUrl())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }
}
