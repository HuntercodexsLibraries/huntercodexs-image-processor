package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorDecrypt {
    private String encryptedImage;
    private String secretKey;
    private String salt;
}
