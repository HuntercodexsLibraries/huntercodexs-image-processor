package com.huntercodexs.image.processor;

import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.contract.item.*;
import com.huntercodexs.image.processor.resource.ImageDimension;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.image.processor.DataBuilder.*;
import static com.huntercodexs.image.processor.enumerator.ImageType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TIP: Use this tests for documentation in order to understand how to use and how to work this library
 * */

class ImageProcessorTests {

    ImageProcessor imageProcessor;
    ImageProcessorContract model;

    public ImageProcessorTests() {
        model = new ImageProcessorContract();
    }

    @Test
    public void imageByteSizeCalculateTest() {

        ImageProcessorBytes imageProcessorBytes = new ImageProcessorBytes();

        imageProcessorBytes.setLength(1);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1byte", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(500);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("500bytes", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(897);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("897bytes", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1023);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1023bytes", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1.0KB", imageProcessor.imageByteSizeCalculate());
    }

    @Test
    public void simulateCalculateKilobytesTest() {

        ImageProcessorBytes imageProcessorBytes = new ImageProcessorBytes();

        imageProcessorBytes.setLength(109693);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("107.12KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1.0KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(2024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1.97KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(2048);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("2.0KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(22024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("21.50KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(722024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("705.10KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(922024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("900.41KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1023000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("999.02KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1023780);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("999.78KB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1024000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("0.97MB", imageProcessor.imageByteSizeCalculate());
    }

    @Test
    public void simulateCalculateMegabytesTest() {

        ImageProcessorBytes imageProcessorBytes = new ImageProcessorBytes();

        imageProcessorBytes.setLength(4264316);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("4.06MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(21276657);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("20.29MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1024000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("0.97MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(2048000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1.95MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(9122024);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("8.69MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(91220244);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("86.99MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(391220244);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("373.09MB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(1024000000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("0.95GB", imageProcessor.imageByteSizeCalculate());
    }

    @Test
    public void simulateCalculateGigabytesTest() {

        ImageProcessorBytes imageProcessorBytes = new ImageProcessorBytes();

        imageProcessorBytes.setLength(2048000000);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("1.90GB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(4096000000L);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("3.81GB", imageProcessor.imageByteSizeCalculate());

        imageProcessorBytes.setLength(7650000000L);
        this.model.setBytesLength(imageProcessorBytes);
        imageProcessor = new ImageProcessor(this.model);
        assertEquals("7.12GB", imageProcessor.imageByteSizeCalculate());
    }

    @Test
    public void isAcceptedTest() {

        ImageProcessorString imageProcessorString = new ImageProcessorString();
        imageProcessorString.setType("bmp");

        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("gif");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("png");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("jpeg");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("jpg");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("tiff");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("psd");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("svg");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("webp");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("nef");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("pdf");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("BMP");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("GIF");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("PNG");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("JPEG");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("JPG");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertTrue(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("TIFF");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("PSD");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("SVG");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("WEBP");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("NEF");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

        imageProcessorString.setType("PDF");
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);
        assertFalse(imageProcessor.isAnAcceptedImage());

    }

    @Test
    public void isImageTest() throws IOException {

        ImageProcessorByte imageProcessorByte = new ImageProcessorByte();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean bmpResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean gifResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean pngResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean jpeg1Result = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean jpeg2Result = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean jpeg3Result = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean jpg1Result = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean jpg2Result = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean tiffResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean psdResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean svgResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean webpResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean nefResult = imageProcessor.isAnImage();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf"));
        this.model.setImageByte(imageProcessorByte);
        imageProcessor = new ImageProcessor(this.model);
        boolean pdfResult = imageProcessor.isAnImage();

        assertTrue(bmpResult);
        assertTrue(gifResult);
        assertTrue(pngResult);
        assertTrue(jpeg1Result);
        assertTrue(jpeg2Result);
        assertTrue(jpeg3Result);
        assertTrue(jpg1Result);
        assertTrue(jpg2Result);
        assertTrue(tiffResult);
        assertFalse(psdResult);
        assertFalse(svgResult);
        assertFalse(webpResult);
        assertTrue(nefResult);
        assertFalse(pdfResult);
    }

    @Test
    public void imageTypeTest() throws IOException {

        ImageProcessorByte imageProcessorByte = new ImageProcessorByte();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(BMP.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(GIF.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PNG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(TIFF.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PSD.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(SVG.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(WEBP.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(NEF.name(), imageProcessor.imageTypeFromBytes());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PDF.name(), imageProcessor.imageTypeFromBytes());

    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        ImageProcessorString imageProcessorString = new ImageProcessorString();

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(BMP.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(GIF.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PNG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(TIFF.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/7-psd/file.psd"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PSD.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/8-svg/file.svg"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(SVG.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/9-webp/file.webp"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(WEBP.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(NEF.name(), imageProcessor.imageTypeFromBinary());

        imageProcessorString.setImage(fileToBinary(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf"));
        this.model.setImageString(imageProcessorString);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PDF.name(), imageProcessor.imageTypeFromBinary());

    }

    @Test
    public void imageFormatTest() throws IOException {

        ImageProcessorByte imageProcessorByte = new ImageProcessorByte();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(BMP.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(GIF.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PNG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file1.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file2.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file3.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file2.jpg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(JPEG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(TIF.name(), imageProcessor.imageFormat());

        /*imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(TIFF.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/7-psd/file.psd"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PSD.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/8-svg/file.svg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(SVG.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/9-webp/file.webp"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(WEBP.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/10-nef/file.NEF"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(NEF.name(), imageProcessor.imageFormat());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/11-pdf/file.pdf"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        assertEquals(PDF.name(), imageProcessor.imageFormat());*/

    }

    @Test
    public void imageDimensionTest() throws IOException {

        ImageProcessorByte imageProcessorByte = new ImageProcessorByte();

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        ImageDimension imageDimensionBmp = imageProcessor.imageDimension();
        assertEquals("1419x1001", imageDimensionBmp.getWidth()+"x"+ imageDimensionBmp.getHeight());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        ImageDimension imageDimensionGif = imageProcessor.imageDimension();
        assertEquals("320x320", imageDimensionGif.getWidth()+"x"+ imageDimensionGif.getHeight());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        ImageDimension imageDimensionPng = imageProcessor.imageDimension();
        assertEquals("512x205", imageDimensionPng.getWidth()+"x"+ imageDimensionPng.getHeight());

        imageProcessorByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        this.model.setImageByte(imageProcessorByte);
        this.imageProcessor = new ImageProcessor(this.model);
        ImageDimension imageDimensionJpeg = imageProcessor.imageDimension();
        assertEquals("273x184", imageDimensionJpeg.getWidth()+"x"+ imageDimensionJpeg.getHeight());

    }

    @Test
    public void imageSizeTest() throws IOException {

        ImageProcessorByte imageByte = new ImageProcessorByte();

        imageByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        this.model.setImageByte(imageByte);
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "BMP  4.06MB",
                "BMP  " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "GIF  107.12KB",
                "GIF  " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "PNG  16.51KB",
                "PNG  " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file-sample-1.png"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "PNG  20.29MB",
                "PNG  " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "JPEG 22.31KB",
                "JPEG " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "JPEG 9.15KB",
                "JPEG " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "JPEG 143.29KB",
                "JPEG " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-1.jpg"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "JPG 1.04MB",
                "JPG " + imageProcessor.imageSize());

        this.model.getImageByte().setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-2.jpg"));
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(
                "JPG 976.75KB",
                "JPG " + imageProcessor.imageSize());

    }

    @Test
    public void imageEncodeTest() throws IOException {

        ImageProcessorByte imageByte = new ImageProcessorByte();

        imageByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        this.model.setImageByte(imageByte);
        imageProcessor = new ImageProcessor(this.model);

        assertEquals(IMAGE_ENCODED_TEST, imageProcessor.imageEncode());
    }

    @Test
    public void imageDecodeTest() throws IOException {
        ImageProcessorString imageProcessorString = new ImageProcessorString();

        imageProcessorString.setImage(IMAGE_ENCODED_TEST);
        this.model.setImageString(imageProcessorString);
        imageProcessor = new ImageProcessor(this.model);

        assertNotNull(imageProcessor.imageDecode());
    }

    @Test
    public void imageEncryptedTest() throws IOException {

        String imgEnc;

        ImageProcessorEncrypt imageProcessorEncrypt = new ImageProcessorEncrypt();
        imageProcessorEncrypt.setSecretKey(SECRET_KEY_TEST);
        imageProcessorEncrypt.setSalt(SALT_TEST);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);
        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);
        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);
        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);
        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);
        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);
    }

    @Test
    public void imageDecryptedTest() throws IOException {
        String imgEnc;
        String imgDec;

        ImageProcessorEncrypt imageProcessorEncrypt = new ImageProcessorEncrypt();
        ImageProcessorDecrypt imageProcessorDecrypt = new ImageProcessorDecrypt();

        imageProcessorEncrypt.setSecretKey(SECRET_KEY_TEST);
        imageProcessorEncrypt.setSalt(SALT_TEST);

        imageProcessorDecrypt.setSecretKey(SECRET_KEY_TEST);
        imageProcessorDecrypt.setSalt(SALT_TEST);

        imageProcessorEncrypt.setImageToEncrypt(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        this.model.setEncrypt(imageProcessorEncrypt);
        this.imageProcessor = new ImageProcessor(this.model);

        imgEnc = imageProcessor.imageEncrypted();
        assertNotNull(imgEnc);

        imageProcessorDecrypt.setEncryptedImage(imgEnc);
        this.model.setDecrypt(imageProcessorDecrypt);
        this.imageProcessor = new ImageProcessor(this.model);

        imgDec = imageProcessor.imageDecrypted();
        assertNotNull(imgDec);
    }

    @Test
    public void imageToMatrix10x10BmpTest() throws IOException {

        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        imageProcessorMatrix.setMatrixSize(10);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(10, imageToMatrix.getFirst().size());
        assertEquals(10, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageToMatrix5x5GifTest() throws IOException {
        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageToMatrix5x5PngTest() throws IOException {
        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageToMatrix20x20PngTest() throws IOException {
        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"));
        imageProcessorMatrix.setMatrixSize(20);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(20, imageToMatrix.getFirst().size());
        assertEquals(20, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageToMatrix5x5JpgTest() throws IOException {
        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);
    }

    @Test
    public void imageToMatrix10x10JpgTest() throws IOException {
        List<List<String>> imageToMatrix;
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"));
        imageProcessorMatrix.setMatrixSize(10);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(10, imageToMatrix.getFirst().size());
        assertEquals(10, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

    }

    @Test
    public void imageFromMatrix10x10BmpTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        imageProcessorMatrix.setMatrixSize(10);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageFromMatrix5x5GifTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageFromMatrix5x5PngTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageFromMatrix20x10PngTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"));
        imageProcessorMatrix.setMatrixSize(20);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageFromMatrix5x5JpgTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        imageProcessorMatrix.setMatrixSize(5);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageFromMatrix10x10JpgTest() throws IOException {
        List<List<String>> imageToMatrix;
        String imageFromMatrix;

        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();

        imageProcessorMatrix.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"));
        imageProcessorMatrix.setMatrixSize(10);

        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageToMatrix = imageProcessor.imageToMatrix();
        assertNotNull(imageToMatrix);

        imageProcessorMatrix.setMatrixImage(imageToMatrix);
        this.model.setMatrix(imageProcessorMatrix);
        this.imageProcessor = new ImageProcessor(this.model);

        imageFromMatrix = imageProcessor.imageFromMatrix();
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageBse64SaveTest() throws IOException {
        ImageProcessorSave imageProcessorSave = new ImageProcessorSave();
        imageProcessorSave.setPath(PATH_TO_TMP_TEST+"/5-jpg-file1.txt");
        imageProcessorSave.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));

        ImageProcessorOperation imageProcessorOperation = new ImageProcessorOperation();
        imageProcessorOperation.setSave(imageProcessorSave);

        this.model.setOperation(imageProcessorOperation);
        this.imageProcessor = new ImageProcessor(this.model);

        assertTrue(imageProcessor.imageBse64Save());
    }

    @Test
    public void imageCopyTest() throws IOException {
        ImageProcessorCopyMove imageProcessorCopyMove = new ImageProcessorCopyMove();
        imageProcessorCopyMove.setOrigin(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg");
        imageProcessorCopyMove.setDestiny(PATH_TO_TMP_TEST+"/5-jpg-file1.jpg");

        ImageProcessorOperation imageProcessorOperation = new ImageProcessorOperation();
        imageProcessorOperation.setCopyMove(imageProcessorCopyMove);

        this.model.setOperation(imageProcessorOperation);
        this.imageProcessor = new ImageProcessor(this.model);

        assertTrue(imageProcessor.imageCopy());
    }

    @Test
    public void imageFlipXTest() throws IOException {

        ImageProcessorByte imageByte = new ImageProcessorByte();

        imageByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));

        this.model.setImageByte(imageByte);
        imageProcessor = new ImageProcessor(this.model);

        byte[] imageFlipXResult = imageProcessor.imageFlipX();
        writeFile(imageFlipXResult, PATH_TO_TMP_TEST+"/file1-flip-x.jpg");
        assertNotNull(imageFlipXResult);
    }

    @Test
    public void imageFlipYTest() throws IOException {

        ImageProcessorByte imageByte = new ImageProcessorByte();

        imageByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));

        this.model.setImageByte(imageByte);
        imageProcessor = new ImageProcessor(this.model);

        byte[] imageFlipYResult = imageProcessor.imageFlipY();
        writeFile(imageFlipYResult, PATH_TO_TMP_TEST+"/file1-flip-y.jpg");
        assertNotNull(imageFlipYResult);
    }

    @Test
    public void imageRotateTest() throws IOException {

        ImageProcessorByte imageByte = new ImageProcessorByte();

        imageByte.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));

        this.model.setImageByte(imageByte);
        imageProcessor = new ImageProcessor(this.model);

        byte[] imageRotateResult = imageProcessor.imageRotate();
        writeFile(imageRotateResult, PATH_TO_TMP_TEST+"/file1-rotate-180.jpg");
        assertNotNull(imageRotateResult);
    }

    @Test
    public void imageResizeTest() throws IOException {

        ImageProcessorOperation imageProcessorOperation = new ImageProcessorOperation();
        ImageProcessorResize imageProcessorResizeMOdel = new ImageProcessorResize();
        imageProcessorResizeMOdel.setImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        imageProcessorResizeMOdel.setWidth(100);
        imageProcessorResizeMOdel.setHeight(65);

        imageProcessorOperation.setResize(imageProcessorResizeMOdel);
        this.model.setOperation(imageProcessorOperation);

        imageProcessor = new ImageProcessor(this.model);

        byte[] imageResizeResult = imageProcessor.imageResize();
        writeFile(imageResizeResult, PATH_TO_TMP_TEST+"/file1-resize.jpg");
        assertNotNull(imageResizeResult);
    }

    @Test
    public void imageCropTest() throws IOException {

        ImageProcessorOperation imageProcessorOperation = new ImageProcessorOperation();
        ImageProcessorCrop imageProcessorCrop = new ImageProcessorCrop();
        imageProcessorCrop.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        imageProcessorCrop.setCropWidth(200);
        imageProcessorCrop.setCropHeight(200);
        imageProcessorCrop.setXAxis(300);
        imageProcessorCrop.setYAxis(300);

        imageProcessorOperation.setCrop(imageProcessorCrop);
        this.model.setOperation(imageProcessorOperation);

        this.imageProcessor = new ImageProcessor(this.model);

        byte[] imageCropResult = imageProcessor.imageCrop();
        writeFile(imageCropResult, PATH_TO_TMP_TEST+"/file-crop.bmp");
        assertNotNull(imageCropResult);
    }

    @Test
    public void imageFragmentTest() throws IOException {
        ImageProcessorFragment imageProcessorFragment = new ImageProcessorFragment();

        imageProcessorFragment.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        imageProcessorFragment.setPath(PATH_TO_TMP_TEST);

        this.model.setFragments(imageProcessorFragment);
        this.imageProcessor = new ImageProcessor(this.model);

        assertTrue(imageProcessor.imageFragment().matches("[0-9a-z]{32}_[a-z]{3,4}"));

        imageProcessorFragment.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        imageProcessorFragment.setPath(PATH_TO_TMP_TEST);

        this.model.setFragments(imageProcessorFragment);
        this.imageProcessor = new ImageProcessor(this.model);

        assertTrue(imageProcessor.imageFragment().matches("[0-9a-z]{32}_[a-z]{3,4}"));
    }

    @Test
    public void imageFragmentRevertTest() throws IOException {

        DataBuilder dataBuilder = new DataBuilder();

        ImageProcessorFragment imageProcessorFragment = new ImageProcessorFragment();

        imageProcessorFragment.setImage(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"));
        imageProcessorFragment.setPath(PATH_TO_TMP_TEST);

        this.model.setFragments(imageProcessorFragment);
        this.imageProcessor = new ImageProcessor(this.model);

        String generatedFolder = imageProcessor.imageFragment();

        this.model.getFragments().setPath(PATH_TO_TMP_TEST + "/" + generatedFolder);

        String revertedImage = this.imageProcessor.imageFragmentRevert();

        dataBuilder.fileCreateBuffered(PATH_TO_TMP_TEST+"/"+generatedFolder+".txt");
        dataBuilder.fileWriteBuffered(revertedImage);
        dataBuilder.fileCloseBuffered();

        assertNotNull(generatedFolder);
        assertNotNull(revertedImage);
    }

}
