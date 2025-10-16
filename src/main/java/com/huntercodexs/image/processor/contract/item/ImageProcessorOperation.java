package com.huntercodexs.image.processor.contract.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageProcessorOperation {
    ImageProcessorCrop crop;
    ImageProcessorSave save;
    ImageProcessorResize resize;
    ImageProcessorCopyMove copyMove;
}
