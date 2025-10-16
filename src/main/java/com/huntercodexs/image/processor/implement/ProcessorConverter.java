package com.huntercodexs.image.processor.implement;

import java.util.List;

public interface ProcessorConverter {

    String imageTypeFromBytesExtractor();

    String imageTypeFromBinaryExtractor();

    List<List<String>> imageToMatrixConverter();

    String imageFromMatrixConverter();

}
