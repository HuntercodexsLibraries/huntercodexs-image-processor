package com.huntercodexs.image.processor.implement.cryptography;

import com.huntercodexs.image.processor.implement.ProcessorCrypto;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.resource.ImageComplement;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Calendar;

public class ImageProcessorCrypto extends ImageComplement implements ProcessorCrypto {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(ImageProcessorCrypto.class);

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorCrypto(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public String encrypt() {
        log.debug("Working on Image Encryption");

        long start = Calendar.getInstance().getTimeInMillis();

        String imageBase64 = new String(Base64.getEncoder().encode(this.imageProcessorContract.getEncrypt().getImageToEncrypt()));

        String imageEncrypted = encryptAesCbc256(
                imageBase64,
                this.imageProcessorContract.getEncrypt().getSecretKey(),
                this.imageProcessorContract.getEncrypt().getSalt());

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);

        log.debug("Elapsed Time: {} seconds", time);
        log.debug("Finishing Image Encryption");

        return String.valueOf(imageEncrypted);
    }

    @Override
    public String decrypt() {
        log.debug("Working on Image Decryption");

        long start = Calendar.getInstance().getTimeInMillis();

        String imageDecrypted = decryptAesCbc256(
                this.imageProcessorContract.getDecrypt().getEncryptedImage(),
                this.imageProcessorContract.getDecrypt().getSecretKey(),
                this.imageProcessorContract.getDecrypt().getSalt());

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);

        log.debug("Finishing Image Decryption, elapsed time: {} seconds", time);

        return String.valueOf(imageDecrypted);
    }

}
