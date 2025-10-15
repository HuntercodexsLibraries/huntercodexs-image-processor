package com.huntercodexs.image.processor.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDimension {
    int width;
    int height;

    public ImageDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
