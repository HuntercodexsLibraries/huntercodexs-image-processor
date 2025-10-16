package com.huntercodexs.image.processor.implement.dimension;

import com.huntercodexs.image.processor.implement.ProcessorDimension;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.implement.other.ImageProcessorOther;
import com.huntercodexs.image.processor.resource.ImageDimension;
import com.huntercodexs.image.processor.resource.ImageComplement;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageProcessorDimension extends ImageComplement implements ProcessorDimension {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(ImageProcessorDimension.class);

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorDimension(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public ImageDimension dimension() throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(
                this.imageProcessorContract.getImageByte().getImage()));

        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);

        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            try {
                reader.setInput(iis);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return new ImageDimension(width, height);
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
        return new ImageDimension(0, 0);
    }

    @Override
    public String size() {

        ImageProcessorOther imageProcessorOther = new ImageProcessorOther(this.imageProcessorContract);

        if (!imageProcessorOther.isImage()) {
            throw new RuntimeException("Invalid Image File");
        }

        byte[] image = this.imageProcessorContract.getImageByte().getImage();

        if (image.length >= 1 && image.length < 1024) {
            return calculateBytes(image.length);
        }

        if (image.length >= 1024 && image.length < 1024000) {
            return calculateKilobytes(image.length);
        }

        if (image.length >= 1024000 && image.length < 1024000000) {
            return calculateMegabytes(image.length);
        }

        return "0.00KB";
    }

}
