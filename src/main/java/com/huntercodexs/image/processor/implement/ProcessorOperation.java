package com.huntercodexs.image.processor.implement;

public interface ProcessorOperation {

    boolean save();
    boolean copy();
    byte[] flipX();
    byte[] flipY();
    byte[] rotate();
    byte[] resize();
    byte[] crop();
}
