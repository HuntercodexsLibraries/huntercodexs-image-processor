package com.huntercodexs.image.processor.implement.codify;

import com.huntercodexs.image.processor.implement.ProcessorCodifier;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.resource.ImageComplement;

import java.util.Base64;

public class ImageProcessorCodifier extends ImageComplement implements ProcessorCodifier {

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorCodifier(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public String encode() {
        return new String(Base64.getEncoder().encode(this.imageProcessorContract.getImageByte().getImage()));
    }

    @Override
    public String decode() {
        return new String(Base64.getDecoder().decode(this.imageProcessorContract.getImageString().getImage()));
    }

}
