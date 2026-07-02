package com.hrstack.utils;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadResponse {
    private String imageUrl;
    private String publicId;
}

