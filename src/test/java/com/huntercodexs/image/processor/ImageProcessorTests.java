package com.huntercodexs.image.processor;

import com.huntercodexs.image.processor.resource.ImageDimension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.image.processor.DataBuilder.*;
import static com.huntercodexs.image.processor.enumerator.ImageType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class ImageProcessorTests {

    @InjectMocks
    ImageProcessor imageProcessor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        imageProcessor = new ImageProcessor();
    }

    @Test
    public void isAcceptedTest() {
        assertTrue(imageProcessor.isAnAcceptedImage("bmp"));
        assertTrue(imageProcessor.isAnAcceptedImage("gif"));
        assertTrue(imageProcessor.isAnAcceptedImage("png"));
        assertTrue(imageProcessor.isAnAcceptedImage("jpeg"));
        assertTrue(imageProcessor.isAnAcceptedImage("jpg"));
        assertFalse(imageProcessor.isAnAcceptedImage("tiff"));
        assertFalse(imageProcessor.isAnAcceptedImage("psd"));
        assertFalse(imageProcessor.isAnAcceptedImage("svg"));
        assertFalse(imageProcessor.isAnAcceptedImage("webp"));
        assertFalse(imageProcessor.isAnAcceptedImage("nef"));
        assertFalse(imageProcessor.isAnAcceptedImage("pdf"));
        assertTrue(imageProcessor.isAnAcceptedImage("BMP"));
        assertTrue(imageProcessor.isAnAcceptedImage("GIF"));
        assertTrue(imageProcessor.isAnAcceptedImage("PNG"));
        assertTrue(imageProcessor.isAnAcceptedImage("JPEG"));
        assertTrue(imageProcessor.isAnAcceptedImage("JPG"));
        assertFalse(imageProcessor.isAnAcceptedImage("TIFF"));
        assertFalse(imageProcessor.isAnAcceptedImage("PSD"));
        assertFalse(imageProcessor.isAnAcceptedImage("SVG"));
        assertFalse(imageProcessor.isAnAcceptedImage("WEBP"));
        assertFalse(imageProcessor.isAnAcceptedImage("NEF"));
        assertFalse(imageProcessor.isAnAcceptedImage("PDF"));
    }

    @Test
    public void isImageTest() throws IOException {
        boolean bmpResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        boolean gifResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        boolean pngResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        boolean jpeg1Result = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        boolean jpeg2Result = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg"));
        boolean jpeg3Result = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg"));
        boolean jpg1Result = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg"));
        boolean jpg2Result = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg"));
        boolean tiffResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff"));
        boolean psdResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd"));
        boolean svgResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg"));
        boolean webpResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp"));
        boolean nefResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF"));
        boolean pdfResult = imageProcessor.isAnImage(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf"));

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

        assertEquals(BMP.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));

        assertEquals(PNG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));

        assertEquals(JPG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg")));

        assertEquals(TIFF.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff")));

        assertEquals(PSD.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/7-psd/file.psd")));

        assertEquals(SVG.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/8-svg/file.svg")));

        assertEquals(WEBP.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/9-webp/file.webp")));

        assertEquals(NEF.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF")));

        assertEquals(PDF.name(), imageProcessor.imageType(fileToByte(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf")));

    }

    @Test
    public void imageTypeBinaryTest() throws IOException {

        assertEquals(BMP.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));

        assertEquals(PNG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/3-png/file.png")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));

        assertEquals(JPG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/5-jpg/file2.jpg")));

        assertEquals(TIFF.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/6-tiff/file.tiff")));

        assertEquals(PSD.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/7-psd/file.psd")));

        assertEquals(SVG.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/8-svg/file.svg")));

        assertEquals(WEBP.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/9-webp/file.webp")));

        assertEquals(NEF.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/10-nef/file.NEF")));

        assertEquals(PDF.name(), imageProcessor.imageType(fileToBinary(PATH_TO_IMAGES_TEST +"/11-pdf/file.pdf")));

    }

    @Test
    public void imageFormatTest() throws IOException {

        assertEquals(BMP.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp")));

        assertEquals(GIF.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif")));

        assertEquals(PNG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png")));

        assertEquals(JPEG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file1.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file2.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/4-jpeg/file3.jpeg")));

        assertEquals(JPEG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg")));

        assertEquals(JPEG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file2.jpg")));

        //assertEquals(TIFF.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff")));
        assertEquals("TIF", imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/6-tiff/file.tiff")));

        //assertEquals(PSD.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/7-psd/file.psd")));

        //assertEquals(SVG.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/8-svg/file.svg")));

        //assertEquals(WEBP.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/9-webp/file.webp")));

        //assertEquals(NEF.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/10-nef/file.NEF")));

        //assertEquals(PDF.name(), imageProcessor.imageFormat(fileToByte(PATH_TO_IMAGES_TEST + "/11-pdf/file.pdf")));

    }

    @Test
    public void imageDimensionTest() throws IOException {
        ImageDimension imageDimensionBmp = imageProcessor.imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp"));
        assertEquals("1419x1001", imageDimensionBmp.getWidth()+"x"+ imageDimensionBmp.getHeight());

        ImageDimension imageDimensionGif = imageProcessor.imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif"));
        assertEquals("320x320", imageDimensionGif.getWidth()+"x"+ imageDimensionGif.getHeight());

        ImageDimension imageDimensionPng = imageProcessor.imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png"));
        assertEquals("512x205", imageDimensionPng.getWidth()+"x"+ imageDimensionPng.getHeight());

        ImageDimension imageDimensionJpeg = imageProcessor.imageDimension(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg"));
        assertEquals("273x184", imageDimensionJpeg.getWidth()+"x"+ imageDimensionJpeg.getHeight());
    }

    @Test
    public void imageByteSizeCalculateTest() {
        assertEquals("1byte", imageProcessor.imageByteSizeCalculate(1));
        assertEquals("500bytes", imageProcessor.imageByteSizeCalculate(500));
        assertEquals("897bytes", imageProcessor.imageByteSizeCalculate(897));
        assertEquals("1023bytes", imageProcessor.imageByteSizeCalculate(1023));
        assertEquals("1.0KB", imageProcessor.imageByteSizeCalculate(1024));
    }

    @Test
    public void simulateCalculateKilobytesTest() {
        assertEquals("107.12KB", imageProcessor.imageByteSizeCalculate(109693));
        assertEquals("1.0KB", imageProcessor.imageByteSizeCalculate(1024));
        assertEquals("1.97KB", imageProcessor.imageByteSizeCalculate(2024));
        assertEquals("2.0KB", imageProcessor.imageByteSizeCalculate(2048));
        assertEquals("21.50KB", imageProcessor.imageByteSizeCalculate(22024));
        assertEquals("705.10KB", imageProcessor.imageByteSizeCalculate(722024));
        assertEquals("900.41KB", imageProcessor.imageByteSizeCalculate(922024));
        assertEquals("999.02KB", imageProcessor.imageByteSizeCalculate(1023000));
        assertEquals("999.78KB", imageProcessor.imageByteSizeCalculate(1023780));
        assertEquals("0.97MB", imageProcessor.imageByteSizeCalculate(1024000));
    }

    @Test
    public void simulateCalculateMegabytesTest() {
        assertEquals("4.06MB", imageProcessor.imageByteSizeCalculate(4264316));
        assertEquals("20.29MB", imageProcessor.imageByteSizeCalculate(21276657));
        assertEquals("0.97MB", imageProcessor.imageByteSizeCalculate(1024000));
        assertEquals("1.95MB", imageProcessor.imageByteSizeCalculate(2048000));
        assertEquals("8.69MB", imageProcessor.imageByteSizeCalculate(9122024));
        assertEquals("86.99MB", imageProcessor.imageByteSizeCalculate(91220244));
        assertEquals("373.09MB", imageProcessor.imageByteSizeCalculate(391220244));
        assertEquals("0.95GB", imageProcessor.imageByteSizeCalculate(1024000000));
    }

    @Test
    public void simulateCalculateGigabytesTest() {
        assertEquals("1.90GB", imageProcessor.imageByteSizeCalculate(2048000000));
        assertEquals("3.81GB", imageProcessor.imageByteSizeCalculate(4096000000L));
        assertEquals("7.12GB", imageProcessor.imageByteSizeCalculate(7650000000L));
    }

    @Test
    public void imageSizeTest() throws IOException {
        assertEquals(
                "BMP  4.06MB",
                "BMP  " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/1-bmp/file.bmp")));
        assertEquals(
                "GIF  107.12KB",
                "GIF  " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/2-gif/file.gif")));
        assertEquals(
                "PNG  16.51KB",
                "PNG  " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file.png")));
        assertEquals(
                "PNG  20.29MB",
                "PNG  " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/3-png/file-sample-1.png")));
        assertEquals(
                "JPEG 22.31KB",
                "JPEG " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file1.jpeg")));
        assertEquals(
                "JPEG 9.15KB",
                "JPEG " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file2.jpeg")));
        assertEquals(
                "JPEG 143.29KB",
                "JPEG " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/4-jpeg/file3.jpeg")));
        assertEquals(
                "JPG 1.04MB",
                "JPG " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-1.jpg")));
        assertEquals(
                "JPG 976.75KB",
                "JPG " + imageProcessor.imageSize(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file-sample-2.jpg")));
    }

    @Test
    public void imageEncodeTest() throws IOException {
        assertEquals(IMAGE_ENCODED_TEST, imageProcessor.imageEncode(fileToByte(PATH_TO_IMAGES_TEST +"/5-jpg/file1.jpg")));
    }

    @Test
    public void imageDecodeTest() throws IOException {
        assertNotNull(imageProcessor.imageDecode(IMAGE_ENCODED_TEST));
    }

    @Test
    public void imageEncryptedTest() throws IOException {
        String imgEnc;
        imgEnc = imageProcessor.imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageProcessor.imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageProcessor.imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageProcessor.imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgEnc = imageProcessor.imageEncrypted(
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"),
                SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
    }

    @Test
    public void imageDecryptedTest() throws IOException {
        String imgEnc;
        String imgDec;

        imgEnc = imageProcessor.imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageProcessor.imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageProcessor.imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageProcessor.imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageProcessor.imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageProcessor.imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageProcessor.imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageProcessor.imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);

        imgEnc = imageProcessor.imageEncrypted(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgEnc);
        imgDec = imageProcessor.imageDecrypted(imgEnc, SECRET_KEY_TEST, SALT_TEST);
        assertNotNull(imgDec);
    }

    @Test
    public void imageToMatrixTest() throws IOException {
        List<List<String>> imageToMatrix;
        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), 10);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(10, imageToMatrix.getFirst().size());
        assertEquals(10, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), 20);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(20, imageToMatrix.getFirst().size());
        assertEquals(20, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 5);
        assertNotNull(imageToMatrix);
        assertNotNull(imageToMatrix.getFirst());
        assertNotNull(imageToMatrix.getLast());
        assertEquals(5, imageToMatrix.getFirst().size());
        assertEquals(5, imageToMatrix.getLast().size());
        matrixPrinter(imageToMatrix, 3);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"), 10);
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
        String imageFromMatrix;

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"), 10);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/2-gif/file.gif"), 5);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file.png"), 5);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/3-png/file-sample-1.png"), 20);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 5);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);

        imageToMatrix = imageProcessor.imageToMatrix(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file-sample-1.jpg"), 10);
        assertNotNull(imageToMatrix);
        imageFromMatrix = imageProcessor.imageFromMatrix(imageToMatrix);
        assertNotNull(imageFromMatrix);
    }

    @Test
    public void imageBse64SaveTest() throws IOException {
        assertTrue(imageProcessor.imageBse64Save(
                PATH_TO_TMP_TEST+"/5-jpg-file1.txt",
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg")));
    }

    @Test
    public void imageCopyTest() throws IOException {
        assertTrue(imageProcessor.imageCopy(
                PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg",
                PATH_TO_TMP_TEST+"/5-jpg-file1.jpg"));
    }

    @Test
    public void imageFragmentTest() throws IOException {
        assertTrue(imageProcessor.imageFragment(
                fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                PATH_TO_TMP_TEST
        ).matches("[0-9a-z]{32}_[a-z]{3,4}"));

        assertTrue(imageProcessor.imageFragment(
                fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"),
                PATH_TO_TMP_TEST
        ).matches("[0-9a-z]{32}_[a-z]{3,4}"));
    }

    @Test
    public void imageFragmentRevertTest() throws IOException {

        DataBuilder dataBuilder = new DataBuilder();

        String generatedFolder = imageProcessor.imageFragment(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), PATH_TO_TMP_TEST);
        String revertedImage = imageProcessor.imageFragmentRevert(PATH_TO_TMP_TEST + "/" + generatedFolder);

        dataBuilder.fileCreateBuffered(PATH_TO_TMP_TEST+"/"+generatedFolder+".txt");
        dataBuilder.fileWriteBuffered(revertedImage);
        dataBuilder.fileCloseBuffered();

        assertNotNull(generatedFolder);
        assertNotNull(revertedImage);
    }

    @Test
    public void imageFlipXTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-flip-x.jpg";
        byte[] imageFlipXResult = imageProcessor.imageFlipX(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageFlipXResult, filePath);
        assertNotNull(imageFlipXResult);
    }

    @Test
    public void imageFlipYTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-flip-y.jpg";
        byte[] imageFlipYResult = imageProcessor.imageFlipY(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageFlipYResult, filePath);
        assertNotNull(imageFlipYResult);
    }

    @Test
    public void imageRotateTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-rotate-180.jpg";
        byte[] imageRotateResult = imageProcessor.imageRotate(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"));
        writeFile(imageRotateResult, filePath);
        assertNotNull(imageRotateResult);
    }

    @Test
    public void imageResizeTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file1-resize.jpg";
        byte[] imageResizeResult = imageProcessor.imageResize(fileToByte(PATH_TO_IMAGES_TEST + "/5-jpg/file1.jpg"), 100, 65);
        writeFile(imageResizeResult, filePath);
        assertNotNull(imageResizeResult);
    }

    @Test
    public void imageCropTest() throws IOException {
        String filePath = PATH_TO_TMP_TEST+"/file-crop.bmp";
        byte[] imageCropResult = imageProcessor.imageCrop(fileToByte(PATH_TO_IMAGES_TEST + "/1-bmp/file.bmp"),
                200,
                200,
                300,
                300);
        writeFile(imageCropResult, filePath);
        assertNotNull(imageCropResult);
    }

}
