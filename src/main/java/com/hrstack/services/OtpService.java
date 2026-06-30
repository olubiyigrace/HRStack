package com.hrstack.services;

import com.hrstack.dto.OtpRequest;
import com.hrstack.dto.OtpVerifyRequest;
import com.hrstack.entities.Otp;
import com.hrstack.repositories.OtpRepository;
import com.hrstack.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;


    public String createOtp(OtpRequest request) {
        String otpCode = AppUtils.generateOtp();

        Otp otp = Otp.builder()
                .email(request.getEmail())
                .otp(passwordEncoder.encode(otpCode))
                .purpose(request.getPurpose())
                .used(false)
                .build();
        otpRepository.save(otp);
        return otpCode;
    }

    public String verifyOtp(OtpVerifyRequest otpVerifyRequest) {
        Optional<Otp> otpOptional = otpRepository.findByEmail(otpVerifyRequest.getEmail());
        if (otpOptional.isEmpty()) {
            return "OTP not found";
        }

        Otp otp = otpOptional.get();
        if (otp.getUsed().equals(true)) {
            return "OTP already used";
        }

        boolean isMatch = passwordEncoder.matches(otpVerifyRequest.getPlainOtp(), otp.getOtp());
        if (!isMatch) {
            return "Invalid OTP";
        }

        otp.setUsed(true);
        otpRepository.save(otp);
        return "OTP verified successfully";
    }
}

