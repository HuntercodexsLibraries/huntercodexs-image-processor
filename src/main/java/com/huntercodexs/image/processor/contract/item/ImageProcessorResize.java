package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorResize {
    private byte[] image;
    private int width;
    private int height;
}
