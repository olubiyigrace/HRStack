package com.hrstack.dto.requestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpRequest {
    private String email;
    private String purpose;
}
