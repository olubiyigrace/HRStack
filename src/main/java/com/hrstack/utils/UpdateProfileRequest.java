package com.hrstack.utils;

import com.hrstack.enums.ReportsTo;
import com.hrstack.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProfileRequest {
    @NotBlank(message = "Name should not be empty")
    @Pattern(regexp = "^[\\p{L}\\p{M}]+(?:[ '-][\\p{L}\\p{M}]+)*$",
            message = "Name can only contain letters, spaces, apostrophes, and hyphens.")
    @Column(updatable = false)
    private String firstName;

    @NotBlank(message = "Name should not be empty")
    @Pattern(regexp = "^[\\p{L}\\p{M}]+(?:[ '-][\\p{L}\\p{M}]+)*$",
            message = "Name can only contain letters, spaces, apostrophes, and hyphens.")
    @Column(updatable = false)
    private String lastName;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotNull(message = "Role cannot be null")
    private Role role;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Phone number should not be empty")
    @Pattern(regexp = "^\\+234(70|80|81|90|91)\\d{8}$", message = "Enter a valid phone number and ensure it starts with +234")
    private String phoneNumber;

    @NotNull(message = "User has to report to someone")
    private ReportsTo reportsTo;
}
