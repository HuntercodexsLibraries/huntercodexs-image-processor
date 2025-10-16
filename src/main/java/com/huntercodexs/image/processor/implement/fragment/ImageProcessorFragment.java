package com.huntercodexs.image.processor.implement.fragment;

import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.contract.item.ImageProcessorByte;
import com.huntercodexs.image.processor.contract.item.ImageProcessorMatrix;
import com.huntercodexs.image.processor.implement.ProcessorFragment;
import com.huntercodexs.image.processor.implement.convert.ImageProcessorConverter;
import com.huntercodexs.image.processor.resource.ImageComplement;
import com.huntercodexs.image.processor.resource.ImageFileWriter;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ImageProcessorFragment extends ImageComplement implements ProcessorFragment {

    ImageProcessorContract imageProcessorContract;

    public ImageProcessorFragment(ImageProcessorContract model) {
        this.imageProcessorContract = model;
    }

    @Override
    public String fragment() {
        com.huntercodexs.image.processor.contract.item.ImageProcessorFragment fragment = this.imageProcessorContract.getFragments();

        ImageProcessorConverter imageProcessorConverter = new ImageProcessorConverter(this.imageProcessorContract);

        setImageByte(fragment.getImage());
        String imageType = imageProcessorConverter.imageTypeFromBytesExtractor().toLowerCase();
        String folderName = randomId()+"_"+imageType;

        String point = "";
        if (fragment.getPath().startsWith(".")) {
            point = ".";
        }

        String filePath = point + fragment.getPath()
                .replaceAll("^\\.", "")
                .replaceAll("/$", "") + "/" + folderName;

        ImageFileWriter imageFileWriter = new ImageFileWriter();
        imageFileWriter.folderCreate(filePath);

        setImageMatrix(fragment.getImage());
        List<List<String>> imageMatrix = imageProcessorConverter.imageToMatrixConverter();

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

    @Override
    public String defragment() {

        String pathToGetFragments = this.imageProcessorContract.getFragments().getPath();

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

    private void setImageByte(byte[] image) {
        ImageProcessorByte imgProcessor = new ImageProcessorByte();
        imgProcessor.setImage(image);
        this.imageProcessorContract.setImageByte(imgProcessor);
    }

    private void setImageMatrix(byte[] image) {
        ImageProcessorMatrix imageProcessorMatrix = new ImageProcessorMatrix();
        imageProcessorMatrix.setMatrixSize(20);
        imageProcessorMatrix.setImage(image);

        this.imageProcessorContract.setMatrix(imageProcessorMatrix);
    }

}
