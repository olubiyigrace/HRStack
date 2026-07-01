package com.hrstack.services;

import com.hrstack.dto.OtpRequest;
import com.hrstack.dto.OtpVerifyRequest;
import com.hrstack.entities.Otp;
import com.hrstack.enums.OtpPurpose;
import com.hrstack.exceptions.InvalidRequestException;
import com.hrstack.repositories.OtpRepository;
import com.hrstack.utils.AppUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OtpService {
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;


    public String createOtp(OtpRequest request) {
        String otpCode = AppUtils.generateOtp();

        Otp otp = Otp.builder()
                .email(request.getEmail())
                .otp(passwordEncoder.encode(otpCode))
                .purpose(request.getPurpose())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .used(false)
                .build();
        otpRepository.save(otp);
        return otpCode;
    }

    public String verifyOtp(OtpVerifyRequest request) {
        Optional<Otp> existingOtp = otpRepository.findTopByEmailAndPurposeOrderByCreatedAtDesc(request.getEmail(), request.getPurpose());
        if (existingOtp.isEmpty()) {
            return "OTP not found";
        }

        Otp newOtp = existingOtp.get();
        if (newOtp.getUsed().equals(true)) {
            return "OTP already used";
        }

        if (newOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            return "OTP has expired";
        }

        boolean isMatch = passwordEncoder.matches(request.getPlainOtp(), newOtp.getOtp());
        if (!isMatch) {
            return "Invalid OTP";
        }
        newOtp.setUsed(true);
        otpRepository.save(newOtp);
        return "OTP verified successfully";
    }

    public String resendOtp(OtpRequest request) {

        checkCooldown(request.getEmail(), request.getPurpose());
        invalidateOldOtps(request.getEmail(), request.getPurpose());

        String otp = createOtp(request);
        createCooldown(request.getEmail(), request.getPurpose());
        return otp;
    }

    private String cooldownKey(String email, OtpPurpose purpose) {
        return "otp:cooldown:" + purpose + ":" + email;
    }

    private void checkCooldown(String email, OtpPurpose purpose) {
        String key = cooldownKey(email, purpose);
        if (redisTemplate.hasKey(key)) {
            throw new InvalidRequestException("Please wait before requesting another OTP.");
        }
    }

    private void createCooldown(String email, OtpPurpose purpose) {
        String key = cooldownKey(email, purpose);
        redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(60));
    }

    private void invalidateOldOtps(String email, OtpPurpose purpose) {
        List<Otp> otps = otpRepository.findAllByEmailAndPurposeAndUsedFalse(email, purpose);
        otps.forEach(otp -> otp.setUsed(true));
        otpRepository.saveAll(otps);
    }
}

