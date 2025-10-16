package com.huntercodexs.image.processor.implement;

import com.huntercodexs.image.processor.resource.ImageDimension;

import java.io.IOException;

public interface ProcessorDimension {

    ImageDimension dimension() throws IOException;
    String size();

}
