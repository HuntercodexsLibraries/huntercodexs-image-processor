package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorSave {
    private String path;
    private byte[] image;
}
