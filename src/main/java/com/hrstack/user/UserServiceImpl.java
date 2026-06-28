package com.hrstack.user;

import com.hrstack.exceptions.DuplicateResourceException;
import com.hrstack.exceptions.InvalidRequestException;
import com.hrstack.otp.OtpRequest;
import com.hrstack.otp.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;


    @Override
    public void create(RegisterUserRequest request) {
        Optional<User> existingUser = userRepository.findByWorkspaceUrl(request.getWorkspaceUrl());
        if(existingUser.isPresent()){
            throw new DuplicateResourceException("Workspace already exists");
        }
        if (!request.getPassword().equals(request.getReEnterPassword())){
            throw new InvalidRequestException("Passwords do not match");
        }
        User newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);

        String otp = otpService.createOtp(
                OtpRequest.builder()
                        .email(request.getEmail())
                        .purpose("verifyAccount")
                        .build()
        );
    }
}
