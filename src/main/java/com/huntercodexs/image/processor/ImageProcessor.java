package com.huntercodexs.image.processor;

import com.huntercodexs.image.processor.enumerator.ImageType;
import com.huntercodexs.image.processor.resource.ImageDimension;
import com.huntercodexs.image.processor.resource.ImageFileWriter;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import static com.huntercodexs.image.processor.constants.ImageProcessorConstants.ITERATION_COUNT;
import static com.huntercodexs.image.processor.constants.ImageProcessorConstants.KEY_LENGTH;

@Service
public class ImageProcessor {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(ImageProcessor.class);

    public String imageByteSizeCalculate(long bytesLength) {
        return calculateBytes(bytesLength);
    }

    public boolean isAnAcceptedImage(String imageType) {
        for (ImageType type : ImageType.values()) {
            if (imageType.toUpperCase().equals(type.name()) && type.isAccepted()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnImage(byte[] image) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            return (bufferedImage != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String imageType(byte[] image) {
        String imageInfo = new String(image).substring(0, 255);
        String imageInfo4 = new String(image).substring(0, 4);
        String imageInfo15 = new String(image).substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    public String imageType(String binaryImage) {
        String imageInfo = binaryImage.substring(0, 255);
        String imageInfo4 = binaryImage.substring(0, 4);
        String imageInfo15 = binaryImage.substring(6, 10);
        return imageTypeCheck(imageInfo, imageInfo4, imageInfo15);
    }

    public String imageFormat(byte[] image) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(image));
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            if (!reader.getFormatName().isEmpty()) {
                return reader.getFormatName().toUpperCase();
            }
        }

        return "UNKNOWN";
    }

    public ImageDimension imageDimension(byte[] image) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(image));
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            try {
                reader.setInput(iis);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return new ImageDimension(width, height);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return new ImageDimension(0, 0);
    }

    public String imageSize(byte[] image) {

        if (!isAnImage(image)) {
            throw new RuntimeException("Invalid Image File");
        }

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

    public String imageEncode(byte[] image) {
        return new String(Base64.getEncoder().encode(image));
    }

    public String imageDecode(String encodedImage) {
        return new String(Base64.getDecoder().decode(encodedImage));
    }

    public String imageEncrypted(byte[] image, String secretKey, String salt) {
        log.debug("Working on Image Encryption");

        long start = Calendar.getInstance().getTimeInMillis();

        String encode = imageEncode(image);
        String imageEncrypted = encryptAesCbc256(encode, secretKey, salt);

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);

        log.debug("Elapsed Time: {} seconds", time);
        log.debug("Finishing Image Encryption");

        return String.valueOf(imageEncrypted);
    }

    public String imageDecrypted(String base64ImageToDecrypt, String secretKey, String salt) {
        log.debug("Working on Image Decryption");

        long start = Calendar.getInstance().getTimeInMillis();

        String imageDecrypted = decryptAesCbc256(base64ImageToDecrypt, secretKey, salt);

        long time = ((Calendar.getInstance().getTimeInMillis() - start) / 1000);

        log.debug("Finishing Image Decryption, elapsed time: {} seconds", time);

        return String.valueOf(imageDecrypted);
    }

    public List<List<String>> imageToMatrix(byte[] image, int matrixSize) {
        if (matrixSize <= 1) {
            return null;
        }

        String encode = imageEncode(image);
        int encodeLength = encode.length();
        int bytesLength = (encodeLength+matrixSize) / matrixSize;
        String[] lines = encode.split("(?<=\\G.{" + bytesLength + "})");

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

    public String imageFromMatrix(List<List<String>> imageMatrix) {

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

    public boolean imageBse64Save(String filenamePath, byte[] image) {
        try {
            ImageFileWriter imageFileWriter = new ImageFileWriter();
            imageFileWriter.fileCreate(filenamePath);
            imageFileWriter.fileWrite(imageEncode(image));
            imageFileWriter.fileClose();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("[EXCEPTION] IMAGE SAVE: " + e.getMessage());
        }
    }

    public boolean imageCopy(String imageSource, String dataDestiny) {
        try {
            byte[] origin = byteFile(imageSource);
            String imageType = imageType(origin);

            String point = "";
            if (dataDestiny.startsWith(".")) {
                point = ".";
            }

            String filenamePahFix = point + dataDestiny
                    .replaceAll("^\\.", "")
                    .split("\\.")[0]+"."+imageType;

            return fileWriter(origin, filenamePahFix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String imageFragment(byte[] image, String pathToSaveFragments) {
        Date date = new Date();

        String imageType = imageType(image).toLowerCase();
        String folderName = md5(String.valueOf(date.getTime()))+"_"+imageType;

        String point = "";
        if (pathToSaveFragments.startsWith(".")) {
            point = ".";
        }

        String filePath = point + pathToSaveFragments
                .replaceAll("^\\.", "")
                .replaceAll("/$", "") + "/" + folderName;

        ImageFileWriter imageFileWriter = new ImageFileWriter();
        imageFileWriter.folderCreate(filePath);

        List<List<String>> imageMatrix = imageToMatrix(image, 20);

        if (imageMatrix == null) {
            throw new RuntimeException("[ERROR] Image Matrix is null");
        }

        int index = 0;
        for (List<String> matrixLine : imageMatrix) {
            for (String matrixColumn : matrixLine) {
                index++;
                try {
                    imageFileWriter.fileCreate(filePath+"/"+folderName+"_"+String.format("%03d", index)+".txt");
                    imageFileWriter.fileWrite(matrixColumn);
                    imageFileWriter.fileClose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return folderName;
    }

    public String imageFragmentRevert(String pathToGetFragments) {

        String point = "";
        if (pathToGetFragments.startsWith(".")) {
            point = ".";
        }

        String fragmentsPath = point + pathToGetFragments
                .replaceAll("^\\.", "")
                .replaceAll("/$", "");

        File file = new File(fragmentsPath);

        String[] filenames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().matches("[0-9a-z]{32}_[a-z]{3,4}_[0-9]{1,3}\\.txt");
            }
        });

        Stream<String> filesSorted = Arrays.stream(filenames).sorted(Comparator.naturalOrder());

        StringBuilder stringBuilder = new StringBuilder();
        filesSorted.forEach(current -> {
            try {
                stringBuilder.append(ioFile(fragmentsPath+"/"+current));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        return String.valueOf(stringBuilder);
    }

    public byte[] imageFlipX(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = imageDimension(image).getWidth();
            String imageType = imageType(image).toLowerCase();

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

    public byte[] imageFlipY(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int height = imageDimension(image).getHeight();
            String imageType = imageType(image).toLowerCase();

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

    public byte[] imageRotate(byte[] image) {
        try {

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int originalType = originalImage.getType();
            int width = imageDimension(image).getWidth();
            int height = imageDimension(image).getHeight();
            String imageType = imageType(image).toLowerCase();

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

    public byte[] imageResize(byte[] image, int width, int height) {
        try {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            String imageType = imageType(image).toLowerCase();

            Image newImage = originalImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage destinationImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] imageCrop(byte[] image, int xAxis, int yAxis, int cropWidth, int cropHeight) {
        try {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            BufferedImage originalImage = ImageIO.read(imageStream);

            String imageType = imageType(image).toLowerCase();

            Image newImage = originalImage.getSubimage(xAxis, yAxis, cropWidth, cropHeight);
            BufferedImage destinationImage = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_RGB);
            destinationImage.getGraphics().drawImage(newImage, 0, 0, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(destinationImage, imageType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    private static String encryptAesCbc256(String strToEncrypt, String secretKey, String salt) {
        try {

            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] encryptedData = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);

        } catch (Exception e) {
            System.out.println("Encrypt Exception: " + e.getMessage());
            return null;
        }
    }

    private static String decryptAesCbc256(String strToDecrypt, String secretKey, String salt) {
        try {

            byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
            byte[] iv = new byte[16];
            System.arraycopy(encryptedData, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = new byte[encryptedData.length - 16];
            System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Decrypt Exception: " + e.getMessage());
            return null;
        }
    }

    private static String imageTypeCheck(String imageInfo, String imageInfo4, String imageInfo15) {
        String fileType;
        if (imageInfo4.equals("BM|\u0011")) {
            fileType = ImageType.BMP.name();
        } else if (imageInfo4.contains("GIF")) {
            fileType = ImageType.GIF.name();
        } else if (imageInfo4.replaceAll("[^A-Z]", "").equals("PNG")) {
            fileType = ImageType.PNG.name();
        } else if (imageInfo4.equals("����") && imageInfo15.equals("JFIF")) {
            fileType = ImageType.JPEG.name();
        } else if (imageInfo4.equals("����")) {
            fileType = ImageType.JPG.name();
        } else if (imageInfo4.contains("II")) {
            fileType = ImageType.TIFF.name();
        } else if (imageInfo4.equals("8BPS")) {
            fileType = ImageType.PSD.name();
        } else if (imageInfo.contains("<svg") && imageInfo.contains("<?xml")) {
            fileType = ImageType.SVG.name();
        } else if (imageInfo.contains("WEBP") && imageInfo4.equals("RIFF")) {
            fileType = ImageType.WEBP.name();
        } else if (imageInfo4.contains("MM")) {
            fileType = ImageType.NEF.name();
        } else if (imageInfo4.contains("%PDF")) {
            fileType = ImageType.PDF.name();
        } else {
            fileType = "UNKNOWN FILE";
        }
        return fileType;

    }

    private static String calculateResult(String[] metrics, String scale) {
        if (metrics[1].length() >= 2) {
            metrics[1] = metrics[1].substring(0, 2);
        } else {
            metrics[1] = String.valueOf(metrics[1].charAt(0));
        }
        return metrics[0]+"."+metrics[1]+scale;
    }

    private static String calculateBytes(long bytesLength) {
        if (bytesLength >= 1 && bytesLength < 1024) {
            if (bytesLength == 1) {
                return bytesLength + "byte";
            } else {
                return bytesLength + "bytes";
            }
        }
        return calculateKilobytes(bytesLength);
    }

    private static String calculateKilobytes(long bytesLength) {
        if (bytesLength >= 1024 && bytesLength < 1024000) {
            String size = String.valueOf(((float) bytesLength) / 1024);
            return calculateResult(size.split("\\."), "KB");
        }
        return calculateMegabytes(bytesLength);
    }

    private static String calculateMegabytes(long bytesLength) {
        if (bytesLength >= 1024000 && bytesLength < 1024000000) {
            float kilobytes = (((float) bytesLength) / 1024);
            float megabytes = (kilobytes / 1024);
            String size = String.valueOf(megabytes);
            return calculateResult(size.split("\\."), "MB");
        }
        return calculateGigabytes(bytesLength);
    }

    private static String calculateGigabytes(long bytesLength) {
        float kilobytes = (((float) bytesLength) / 1024);
        float megabytes = (kilobytes / 1024);
        float gigabytes = (megabytes / 1024);
        String size = String.valueOf(gigabytes);
        return calculateResult(size.split("\\."), "GB");
    }

    private static byte[] byteFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toByteArray(fis);
    }

    private static String ioFile(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toString(fis);
    }

    private static boolean fileWriter(byte[] data, String path) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(data);
            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Is not possible to write in the file: " + ex.getMessage());
        }

        return true;
    }

}
