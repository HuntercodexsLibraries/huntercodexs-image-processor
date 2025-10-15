package com.huntercodexs.image.processor.resource;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
public class ImageFileWriter {

    public BufferedWriter bufferedWriter;

    public boolean folderCreate(String path) {
        try {

            File file = new File(path);

            if (file.mkdirs()) {
                return true;
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Folder not created: " + ex.getMessage());
        }

        System.out.println("[ERROR] Folder not created: " + path);
        return false;
    }

    public void fileCreate(String filepath) throws FileNotFoundException {
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

    public void fileWrite(String data) {
        try {
            this.bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileClose() throws IOException {
        this.bufferedWriter.close();
    }
}
