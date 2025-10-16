package com.huntercodexs.image.processor.implement.other;

import com.huntercodexs.image.processor.enumerator.ImageType;
import com.huntercodexs.image.processor.implement.ProcessorOther;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.resource.ImageComplement;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageProcessorOther extends ImageComplement implements ProcessorOther {

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorOther(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public String calculateBytes() {
        return imageCalculate(this.imageProcessorContract.getBytesLength().getLength());
    }

    @Override
    public boolean isAcceptable() {
        for (ImageType type : ImageType.values()) {
            if (this.imageProcessorContract.getImageString().getType().toUpperCase().equals(type.name()) && type.isAccepted()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isImage() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(this.imageProcessorContract.getImageByte().getImage()));
            return (bufferedImage != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String formatName() throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(
                this.imageProcessorContract.getImageByte().getImage()));

        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);

        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            if (!reader.getFormatName().isEmpty()) {
                return reader.getFormatName().toUpperCase();
            }
        }
        return "UNKNOWN";
    }

    private String imageCalculate(long bytesLength) {
        return calculateBytes(bytesLength);
    }

}
