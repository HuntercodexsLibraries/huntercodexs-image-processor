package com.huntercodexs.image.processor.implement.convert;

import com.huntercodexs.image.processor.implement.ProcessorConverter;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.resource.ImageComplement;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class ImageProcessorConverter extends ImageComplement implements ProcessorConverter {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(ImageProcessorConverter.class);

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorConverter(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public String imageTypeFromBytesExtractor() {
        byte[] image = this.imageProcessorContract.getImageByte().getImage();
        String imageInfo = new String(image).substring(0, 255);
        String imageInfo4 = new String(image).substring(0, 4);
        String imageInfo15 = new String(image).substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    @Override
    public String imageTypeFromBinaryExtractor() {
        String binaryImage = this.imageProcessorContract.getImageString().getImage();
        String imageInfo = binaryImage.substring(0, 255);
        String imageInfo4 = binaryImage.substring(0, 4);
        String imageInfo15 = binaryImage.substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    @Override
    public List<List<String>> imageToMatrixConverter() {

        int matrixSize;

        try {
            matrixSize = this.imageProcessorContract.getMatrix().getMatrixSize();
        } catch (RuntimeException e) {
            matrixSize = 1;
            log.warn(e.getMessage());
        }

        if (matrixSize <= 1) {
            return null;
        }

        String imageBase64 = new String(Base64.getEncoder().encode(this.imageProcessorContract.getMatrix().getImage()));
        int encodeLength = imageBase64.length();
        int bytesLength = (encodeLength+matrixSize) / matrixSize;
        String[] lines = imageBase64.split("(?<=\\G.{" + bytesLength + "})");

        List<List<String>> imageMatrix = new ArrayList<>();

        for (String line : lines) {

            List<String> matrixColumns = new ArrayList<>();

            int lineLength = line.length();
            int columnsLength = (lineLength+matrixSize) / matrixSize;
            String[] columns = line.split("(?<=\\G.{" + columnsLength + "})");

            Collections.addAll(matrixColumns, columns);

            imageMatrix.add(matrixColumns);
        }

        return imageMatrix;
    }

    @Override
    public String imageFromMatrixConverter() {

        List<List<String>> imageMatrix = this.imageProcessorContract.getMatrix().getMatrixImage();

        StringBuilder stringBuilder = new StringBuilder();
        int matrixSize = imageMatrix.size();

        for (List<String> matrixLine : imageMatrix) {
            if (matrixLine.size() != matrixSize) {
                throw new RuntimeException("WRONG MATRIX SIZE: " + matrixSize +"x"+ matrixLine.size());
            }

            for (String matrixColumn : matrixLine) {
                stringBuilder.append(matrixColumn);
            }

        }

        return String.valueOf(stringBuilder);
    }

}
