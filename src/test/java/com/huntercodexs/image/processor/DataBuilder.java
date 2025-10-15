package com.huntercodexs.image.processor;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DataBuilder {

    public static final String SALT_TEST = "1";
    public static final String IV_TEST = "F1F2F3F4F5F6F7F8";
    public static final String SECRET_KEY_TEST = "F1F2F3F4F5F6F7F8F1F2F3F4F5F6F7F8";

    public static final String PATH_TO_IMAGES_TEST = "./src/test/resources/images";
    public static final String PATH_TO_TMP_TEST = "./src/test/resources/tmp";
    public static final String PROPERTIES_MESSAGE_FILE_TEST = "test_messages";

    public static final String IMAGE_ENCODED_TEST = "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";

    public BufferedWriter bufferedWriter;

    public void fileCreateBuffered(String filepath) throws FileNotFoundException {
        File file = new File(filepath);

        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("ERROR: File Not deleted: " + filepath);
            }
        }

        OutputStream os = new FileOutputStream(filepath, true);
        Writer wr = new OutputStreamWriter(os);
        this.bufferedWriter = new BufferedWriter(wr);
    }

    public void fileWriteBuffered(String data) {
        try {
            this.bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileCloseBuffered() throws IOException {
        this.bufferedWriter.close();
    }

    public static boolean writeFile(byte[] data, String path) {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(data);
            fileOutputStream.close();

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Is not possible to write in the file: " + ex.getMessage());
        }

        return true;
    }

    public static byte[] fileToByte(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return IOUtils.toByteArray(fis);
    }

    public static String fileToBinary(String filenamePath) throws IOException {
        FileInputStream fis = new FileInputStream(filenamePath);
        return new String(IOUtils.toByteArray(fis), StandardCharsets.UTF_8);
    }

    public static void stdout(Object... inputs) {
        for (Object input : inputs) {
            System.out.println(input);
        }
    }

    public static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    public static void matrixPrinter(List<List<String>> matrix, int columnSize) {
        if (columnSize == 1) {
            System.out.println("MATRIX PRINTER SAY: [ERROR] MATRIX IS NOT A MATRIX (3X3)");
            return;
        }

        System.out.println("[MATRIX PRINTER]");
        for (List<String> line : matrix) {
            System.out.print("[");
            int columnCounter = 0;

            for (String column : line) {

                if (columnSize > 1) {
                    if (columnCounter < line.size()-1) {
                        System.out.print(column.substring(0, columnSize) + ", ");
                    } else {
                        System.out.print(column.substring(0, columnSize));
                    }
                } else {
                    if (columnCounter < line.size()-1) {
                        System.out.print(column + ", ");
                    } else {
                        System.out.print(column);
                    }
                }

                columnCounter++;
            }
            System.out.println("]");
        }
    }

}
