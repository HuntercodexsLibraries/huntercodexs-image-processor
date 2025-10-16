package com.huntercodexs.image.processor.implement;

import java.io.IOException;

public interface ProcessorOther {

    String calculateBytes();
    boolean isAcceptable();
    boolean isImage();
    String formatName() throws IOException;

}
