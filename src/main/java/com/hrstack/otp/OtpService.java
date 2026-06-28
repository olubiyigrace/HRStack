package com.hrstack.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;

    private static final SecureRandom RANDOM = new SecureRandom();

    public String createOtp(OtpRequest request) {
        String otpCode = generateOtp();

        Otp otp = Otp.builder()
                .email(request.getEmail())
                .otp(passwordEncoder.encode(otpCode))
                .purpose(request.getPurpose())
                .used(false)
                .build();
        otpRepository.save(otp);
        return otpCode;
    }

    private String generateOtp() {
        return String.valueOf(100000 + RANDOM.nextInt(900000));
    }
}

