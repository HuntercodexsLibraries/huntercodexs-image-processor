package com.huntercodexs.image.processor.contract;

import com.huntercodexs.image.processor.contract.item.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorContract {
    ImageProcessorString imageString;
    ImageProcessorByte imageByte;
    ImageProcessorBytes bytesLength;

    ImageProcessorDecrypt decrypt;
    ImageProcessorEncrypt encrypt;

    ImageProcessorFragment fragments;
    ImageProcessorMatrix matrix;

    ImageProcessorOperation operation;
}
