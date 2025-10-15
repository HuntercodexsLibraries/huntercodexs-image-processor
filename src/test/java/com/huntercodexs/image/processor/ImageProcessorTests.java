package com.huntercodexs.image.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.image.processor.DataBuilder.*;
import static com.huntercodexs.image.processor.ImageProcessor.*;
import static com.huntercodexs.image.processor.ImageProcessor.ImageType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class ImageProcessorTests {

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void isAcceptedTest() {
        assertTrue(isAnAcceptedImage("bmp"));
        assertTrue(isAnAcceptedImage("gif"));
        assertTrue(isAnAcceptedImage("png"));
        assertTrue(isAnAcceptedImage("jpeg"));
        assertTrue(isAnAcceptedImage("jpg"));
        assertFalse(isAnAcceptedImage("tiff"));
        assertFalse(isAnAcceptedImage("psd"));
        assertFalse(isAnAcceptedImage("svg"));
        assertFalse(isAnAcceptedImage("webp"));
        assertFalse(isAnAcceptedImage("nef"));
        assertFalse(isAnAcceptedImage("pdf"));
        assertTrue(isAnAcceptedImage("BMP"));
        assertTrue(isAnAcceptedImage("GIF"));
        assertTrue(isAnAcceptedImage("PNG"));
        assertTrue(isAnAcceptedImage("JPEG"));
        assertTrue(isAnAcceptedImage("JPG"));
        assertFalse(isAnAcceptedImage("TIFF"));
        assertFalse(isAnAcceptedImage("PSD"));
        assertFalse(isAnAcceptedImage("SVG"));
        assertFalse(isAnAcceptedImage("WEBP"));
        assertFalse(isAnAcceptedImage("NEF"));
        assertFalse(isAnAcceptedImage("PDF"));
    }

    @Test
    public void isImageTest() throws IOException {
        boolean bmpResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        boolean gifResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        boolean pngResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        boolean jpeg1Result = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        boolean jpeg2Result = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        boolean jpeg3Result = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        boolean jpg1Result = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        boolean jpg2Result = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg"));
        boolean tiffResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff"));
        boolean psdResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd"));
        boolean svgResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg"));
        boolean webpResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp"));
        boolean nefResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF"));
        boolean pdfResult = isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf"));

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

        assertEquals(BMP.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));

        assertEquals(PNG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png")));

        assertEquals(JPEG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));

        assertEquals(JPG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg")));

        assertEquals(TIFF.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff")));

        assertEquals(PSD.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd")));

        assertEquals(SVG.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg")));

        assertEquals(WEBP.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp")));

        assertEquals(NEF.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF")));

        assertEquals(PDF.name(), imageType(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf")));

    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        assertEquals(BMP.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));

        assertEquals(PNG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/3-png/file.png")));

        assertEquals(JPEG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));

        assertEquals(JPG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg")));

        assertEquals(TIFF.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff")));

        assertEquals(PSD.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/7-psd/file.psd")));

        assertEquals(SVG.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/8-svg/file.svg")));

        assertEquals(WEBP.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/9-webp/file.webp")));

        assertEquals(NEF.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF")));

        assertEquals(PDF.name(), imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf")));

    }

    @Test
    public void imageFormatTest() throws IOException {

        assertEquals(BMP.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif")));

        assertEquals(PNG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png")));

        assertEquals(JPEG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg")));

        assertEquals(JPEG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file2.jpg")));

        //assertEquals(TIFF.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff")));
        assertEquals("TIF", imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff")));

        //assertEquals(PSD.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/7-psd/file.psd")));

        //assertEquals(SVG.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/8-svg/file.svg")));

        //assertEquals(WEBP.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/9-webp/file.webp")));

        //assertEquals(NEF.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/10-nef/file.NEF")));

        //assertEquals(PDF.name(), imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/11-pdf/file.pdf")));

    }

    @Test
    public void imageDimensionTest() throws IOException {
        Dimension dimensionBmp = imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        assertEquals("1419x1001", dimensionBmp.getWidth()+"x"+dimensionBmp.getHeight());

        Dimension dimensionGif = imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        assertEquals("320x320", dimensionGif.getWidth()+"x"+dimensionGif.getHeight());

        Dimension dimensionPng = imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        assertEquals("512x205", dimensionPng.getWidth()+"x"+dimensionPng.getHeight());

        Dimension dimensionJpeg = imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        assertEquals("273x184", dimensionJpeg.getWidth()+"x"+dimensionJpeg.getHeight());
    }

    @Test
    public void imageByteSizeCalculateTest() {
        assertEquals("1byte", imageByteSizeCalculate(1));
        assertEquals("500bytes", imageByteSizeCalculate(500));
        assertEquals("897bytes", imageByteSizeCalculate(897));
        assertEquals("1023bytes", imageByteSizeCalculate(1023));
        assertEquals("1.0KB", imageByteSizeCalculate(1024));
    }

    @Test
    public void simulateCalculateKilobytesTest() {
        assertEquals("107.12KB", imageByteSizeCalculate(109693));
        assertEquals("1.0KB", imageByteSizeCalculate(1024));
        assertEquals("1.97KB", imageByteSizeCalculate(2024));
        assertEquals("2.0KB", imageByteSizeCalculate(2048));
        assertEquals("21.50KB", imageByteSizeCalculate(22024));
        assertEquals("705.10KB", imageByteSizeCalculate(722024));
        assertEquals("900.41KB", imageByteSizeCalculate(922024));
        assertEquals("999.02KB", imageByteSizeCalculate(1023000));
        assertEquals("999.78KB", imageByteSizeCalculate(1023780));
        assertEquals("0.97MB", imageByteSizeCalculate(1024000));
    }

    @Test
    public void simulateCalculateMegabytesTest() {
        assertEquals("4.06MB", imageByteSizeCalculate(4264316));
        assertEquals("20.29MB", imageByteSizeCalculate(21276657));
        assertEquals("0.97MB", imageByteSizeCalculate(1024000));
        assertEquals("1.95MB", imageByteSizeCalculate(2048000));
        assertEquals("8.69MB", imageByteSizeCalculate(9122024));
        assertEquals("86.99MB", imageByteSizeCalculate(91220244));
        assertEquals("373.09MB", imageByteSizeCalculate(391220244));
        assertEquals("0.95GB", imageByteSizeCalculate(1024000000));
    }

    @Test
    public void simulateCalculateGigabytesTest() {
        assertEquals("1.90GB", imageByteSizeCalculate(2048000000));
        assertEquals("3.81GB", imageByteSizeCalculate(4096000000L));
        assertEquals("7.12GB", imageByteSizeCalculate(7650000000L));
    }

    @Test
    public void imageSizeTest() throws IOException {
        assertEquals(
                "BMP  4.06MB",
                "BMP  " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));
        assertEquals(
                "GIF  107.12KB",
                "GIF  " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));
        assertEquals(
                "PNG  16.51KB",
                "PNG  " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png")));
        assertEquals(
                "PNG  20.29MB",
                "PNG  " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file-sample-1.png")));
        assertEquals(
                "JPEG 22.31KB",
                "JPEG " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));
        assertEquals(
                "JPEG 9.15KB",
                "JPEG " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));
        assertEquals(
                "JPEG 143.29KB",
                "JPEG " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));
        assertEquals(
                "JPG 1.04MB",
                "JPG " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-1.jpg")));
        assertEquals(
                "JPG 976.75KB",
                "JPG " + imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-2.jpg")));
    }

    @Test
    public void imageEncodeTest() throws IOException {
        assertEquals(IMAGE_ENCODED_TEST, imageEncode(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));
    }

    @Test
    public void imageDecodeTest() throws IOException {
        assertNotNull(imageDecode(IMAGE_ENCODED_TEST));
    }

    @Test
    public void imageEncryptedTest() throws IOException {
        String imgEnc;
        imgEnc = imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
    }

    @Test
    public void imageDecryptedTest() throws IOException {
        String imgEnc;
        String imgDec;

        imgEnc = imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);
    }

    @Test
    public void imageToMatrixTest() throws IOException {
        List<List<String>> imageToMatrix;
        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), 10);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(10, imageToMatrix.getFirst().size());
        assertEquals(10, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), 20);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(20, imageToMatrix.getFirst().size());
        assertEquals(20, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"), 10);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(10, imageToMatrix.getFirst().size());
        assertEquals(10, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

    }

    @Test
    public void imageFromMatrixTest() throws IOException {
        List<List<String>> imageToMatrix;

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), 10);
        assertNotNull(imageToMatrix);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), 5);
        assertNotNull(imageToMatrix);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), 5);
        assertNotNull(imageToMatrix);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), 20);
        assertNotNull(imageToMatrix);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 5);
        assertNotNull(imageToMatrix);

        imageToMatrix = imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"), 10);
        assertNotNull(imageToMatrix);
    }

    @Test
    public void imageBse64SaveTest() throws IOException {
        assertTrue(imageBse64Save(
                PATH_TO_TMP_TEST+"/5-jpg-file1.txt",
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg")));
    }

    @Test
    public void imageCopyTest() throws IOException {
        assertTrue(imageCopy(
                PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg",
                PATH_TO_TMP_TEST+"/5-jpg-file1.jpg"));
    }

    @Test
    public void imageFragmentTest() throws IOException {
        assertTrue(imageFragment(
                fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                PATH_TO_TMP_TEST
        ).matches("[0-9a-z]{32}_[a-z]{3,4}"));

        assertTrue(imageFragment(
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"),
                PATH_TO_TMP_TEST
        ).matches("[0-9a-z]{32}_[a-z]{3,4}"));
    }

    @Test
    public void imageFragmentRevertTest() throws IOException {

        DataBuilder dataBuilder = new DataBuilder();

        String generatedFolder = imageFragment(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), PATH_TO_TMP_TEST);
        String revertedImage = imageFragmentRevert(PATH_TO_TMP_TEST + "/" + generatedFolder);

        dataBuilder.fileCreateBuffered(PATH_TO_TMP_TEST+"/"+generatedFolder+".txt");
        dataBuilder.fileWriteBuffered(revertedImage);
        dataBuilder.fileCloseBuffered();

        assertNotNull(generatedFolder);
        assertNotNull(revertedImage);
    }

    @Test
    public void imageFlipXTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-flip-x.jpg";
        byte[] imageFlipXResult = imageFlipX(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageFlipXResult, filePath);
        assertNotNull(imageFlipXResult);
    }

    @Test
    public void imageFlipYTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-flip-y.jpg";
        byte[] imageFlipYResult = imageFlipY(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageFlipYResult, filePath);
        assertNotNull(imageFlipYResult);
    }

    @Test
    public void imageRotateTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-rotate-180.jpg";
        byte[] imageRotateResult = imageRotate(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageRotateResult, filePath);
        assertNotNull(imageRotateResult);
    }

    @Test
    public void imageResizeTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-resize.jpg";
        byte[] imageResizeResult = imageResize(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 100, 65);
        writeFile(imageResizeResult, filePath);
        assertNotNull(imageResizeResult);
    }

    @Test
    public void imageCropTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file-crop.bmp";
        byte[] imageCropResult = imageCrop(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                200,
                200,
                300,
                300);
        writeFile(imageCropResult, filePath);
        assertNotNull(imageCropResult);
    }

}
