package com.huntercodexs.image.processor.implement.operation;

import com.huntercodexs.image.processor.implement.ProcessorOperation;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.contract.item.ImageProcessorByte;
import com.huntercodexs.image.processor.contract.item.ImageProcessorCrop;
import com.huntercodexs.image.processor.contract.item.ImageProcessorResize;
import com.huntercodexs.image.processor.implement.convert.ImageProcessorConverter;
import com.huntercodexs.image.processor.implement.dimension.ImageProcessorDimension;
import com.huntercodexs.image.processor.resource.ImageFileWriter;
import com.huntercodexs.image.processor.resource.ImageComplement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageProcessorOperation extends ImageComplement implements ProcessorOperation {

    ImageProcessorContract imageProcessorContract;
    ImageProcessorDimension imageProcessorDimension;
    ImageProcessorConverter imageProcessorConverter;

    public ImageProcessorOperation(ImageProcessorContract model) {
        this.imageProcessorContract = model;
        this.imageProcessorDimension = new ImageProcessorDimension(model);
        this.imageProcessorConverter = new ImageProcessorConverter(model);
    }

    @Override
    public boolean save() {
        try {
            ImageFileWriter imageFileWriter = new ImageFileWriter();
            imageFileWriter.fileCreate(this.imageProcessorContract.getOperation().getSave().getPath());
            imageFileWriter.fileWrite(new String(Base64.getEncoder().encode(this.imageProcessorContract.getOperation().getSave().getImage())));
            imageFileWriter.fileClose();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] IMAGE SAVE: " + e.getMessage());
        }
    }

    @Override
    public boolean copy() {
        try {
            byte[] origin = byteFile(this.imageProcessorContract.getOperation().getCopyMove().getOrigin());

            setImageByte(origin);
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor();

            String point = "";
            if (this.imageProcessorContract.getOperation().getCopyMove().getOrigin().startsWith(".")) {
                point = ".";
            }

            String filenamePahFix = point + this.imageProcessorContract.getOperation().getCopyMove().getDestiny()
                    .replaceAll("^\\.", "")
                    .split("\\.")[0]+"."+imageType;

            return fileWriter(origin, filenamePahFix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] flipX() {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(this.imageProcessorContract.getImageByte().getImage());
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = this.imageProcessorDimension.dimension().getWidth();
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();

            /*Flip X*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(-1.0, 1.0);
            affineTransform.translate(-width, 0);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Flip X: " + ex.getMessage());
        }
    }

    @Override
    public byte[] flipY() {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(this.imageProcessorContract.getImageByte().getImage());
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int height = this.imageProcessorDimension.dimension().getHeight();
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();

            /*Flip Y*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(1.0, -1.0);
            affineTransform.translate(0, -height);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Flip Y: " + ex.getMessage());
        }
    }

    @Override
    public byte[] rotate() {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(this.imageProcessorContract.getImageByte().getImage());
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = this.imageProcessorDimension.dimension().getWidth();
            int height = this.imageProcessorDimension.dimension().getHeight();
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();

            /*Rotate - 180 degrees*/
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.translate(width, height);
            affineTransform.rotate(Math.PI);

            BufferedImage destinationImage = new BufferedImage(originalWidth, originalHeight, originalType);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);
            destinationImage = affineTransformOp.filter(originalImage, destinationImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Image Rotate: " + ex.getMessage());
        }
    }

    @Override
    public byte[] resize() {
        try {

            ImageProcessorResize operation = this.imageProcessorContract.getOperation().getResize();

            ByteArrayInputStream imageStream = new ByteArrayInputStream(operation.getImage());
            BufferedImage originalImage = ImageIO.read(imageStream);

            setImageByte(operation.getImage());
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();

            Image newImage = originalImage.getScaledInstance(
                    operation.getWidth(),
                    operation.getHeight(),
                    Image.SCALE_DEFAULT);
            BufferedImage destinationImage = new BufferedImage(
                    operation.getWidth(),
                    operation.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] crop() {
        try {

            ImageProcessorCrop operation = this.imageProcessorContract.getOperation().getCrop();

            ByteArrayInputStream imageStream = new ByteArrayInputStream(operation.getImage());
            BufferedImage originalImage = ImageIO.read(imageStream);

            setImageByte(operation.getImage());
            String imageType = this.imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();

            Image newImage = originalImage.getSubimage(
                    operation.getXAxis(),
                    operation.getXAxis(),
                    operation.getCropWidth(),
                    operation.getCropHeight());

            BufferedImage destinationImage = new BufferedImage(
                    operation.getCropWidth(),
                    operation.getCropHeight(),
                    BufferedImage.TYPE_INT_RGB);

            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setImageByte(byte[] image) {
        ImageProcessorByte imgProcessor = new ImageProcessorByte();
        imgProcessor.setImage(image);
        this.imageProcessorContract.setImageByte(imgProcessor);
    }
}
