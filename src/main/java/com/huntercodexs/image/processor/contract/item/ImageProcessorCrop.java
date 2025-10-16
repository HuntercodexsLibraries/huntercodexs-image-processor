package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorCrop {
    private byte[] image;
    private int xAxis;
    private int yAxis;
    private int cropWidth;
    private int cropHeight;
}
