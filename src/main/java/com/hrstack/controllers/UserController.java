package com.hrstack.controllers;


import com.hrstack.services.OtpService;
import com.hrstack.dto.OtpVerifyRequest;
import com.hrstack.dto.RegisterUserRequest;
import com.hrstack.services.UserService;
import com.hrstack.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OtpService otpService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterUserRequest request) {
        userService.create(request);
        return ResponseEntity.ok(ApiResponse.success(true, "Registration successful. Check your email for the verification code.", null)
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@Valid @RequestBody OtpVerifyRequest request) {
        String response = otpService.verifyOtp(request);
        return ResponseEntity.ok(ApiResponse.success(true, response, null));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<ApiResponse<String>> resendOtp(@RequestParam String email) {
        userService.resendVerificationOtp(email);
        return ResponseEntity.ok(ApiResponse.success(true, "OTP sent successfully.", null));
    }
}
