package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorEncrypt {
    private byte[] imageToEncrypt;
    private String secretKey;
    private String salt;
}
