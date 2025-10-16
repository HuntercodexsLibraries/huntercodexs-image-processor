package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ImageProcessorMatrix {
    private byte[] image;
    private int matrixSize;
    private List<List<String>> matrixImage;
}
