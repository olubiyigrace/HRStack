package com.hrstack.services;

import com.hrstack.dto.requestDto.RegisterAdminRequest;
import com.hrstack.dto.requestDto.RefreshTokenRequest;
import com.hrstack.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void create(RegisterAdminRequest request);
    void resendVerificationOtp(String email);
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(RefreshTokenRequest request);
    void changePassword(ChangePasswordRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(String token, ResetPasswordRequest request);
    void logout(HttpServletRequest request);
    void update(String id, UpdateProfileRequest registerUserRequest);
    void uploadProfilePicture(MultipartFile file);
}
