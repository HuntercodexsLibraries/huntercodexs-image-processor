package com.huntercodexs.image.processor;

import com.huntercodexs.image.processor.implement.*;
import com.huntercodexs.image.processor.implement.codify.ImageProcessorCodifier;
import com.huntercodexs.image.processor.implement.convert.ImageProcessorConverter;
import com.huntercodexs.image.processor.implement.cryptography.ImageProcessorCrypto;
import com.huntercodexs.image.processor.implement.dimension.ImageProcessorDimension;
import com.huntercodexs.image.processor.implement.fragment.ImageProcessorFragment;
import com.huntercodexs.image.processor.implement.operation.ImageProcessorOperation;
import com.huntercodexs.image.processor.implement.other.ImageProcessorOther;
import com.huntercodexs.image.processor.contract.ImageProcessorContract;
import com.huntercodexs.image.processor.resource.ImageDimension;
import com.huntercodexs.image.processor.resource.ImageComplement;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ImageProcessor extends ImageComplement {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(ImageProcessor.class);

    ImageProcessorContract processorModel;

    private final ProcessorCodifier processorCodifier;
    private final ProcessorConverter processorConverter;
    private final ProcessorCrypto processorCrypto;
    private final ProcessorDimension processorDimension;
    private final ProcessorFragment processorFragment;
    private final ProcessorOperation processorOperation;
    private final ProcessorOther processorOther;

    public ImageProcessor(ImageProcessorContract model) {
        this.processorModel = model;
        this.processorCodifier = new ImageProcessorCodifier(model);
        this.processorConverter = new ImageProcessorConverter(model);
        this.processorCrypto = new ImageProcessorCrypto(model);
        this.processorDimension = new ImageProcessorDimension(model);
        this.processorFragment = new ImageProcessorFragment(model);
        this.processorOperation = new ImageProcessorOperation(model);
        this.processorOther = new ImageProcessorOther(model);
    }

    /*Codify*/

    public String imageEncode() {
        return processorCodifier.encode();
    }

    public String imageDecode() {
        return processorCodifier.decode();
    }

    /*Convert*/

    public String imageTypeFromBytes() {
        return processorConverter.imageTypeFromBytesExtractor();
    }

    public String imageTypeFromBinary() {
        return this.processorConverter.imageTypeFromBinaryExtractor();
    }

    public List<List<String>> imageToMatrix() {
        return this.processorConverter.imageToMatrixConverter();
    }

    public String imageFromMatrix() {
        return this.processorConverter.imageFromMatrixConverter();
    }

    /*Cryptography*/

    public String imageEncrypted() {
        return this.processorCrypto.encrypt();
    }

    public String imageDecrypted() {
        return this.processorCrypto.decrypt();
    }

    /*Dimension*/

    public ImageDimension imageDimension() throws IOException {
        return this.processorDimension.dimension();
    }

    /*Fragment*/

    public String imageFragment() {
        return this.processorFragment.fragment();
    }

    public String imageFragmentRevert() {
        return this.processorFragment.defragment();
    }

    public String imageSize() {
        return this.processorDimension.size();
    }

    /*Operation*/

    public boolean imageBse64Save() {
        return this.processorOperation.save();
    }

    public boolean imageCopy() {
        return this.processorOperation.copy();
    }

    public byte[] imageFlipX() {
        return this.processorOperation.flipX();
    }

    public byte[] imageFlipY() {
        return this.processorOperation.flipY();
    }

    public byte[] imageRotate() {
        return this.processorOperation.rotate();
    }

    public byte[] imageResize() {
        return this.processorOperation.resize();
    }

    public byte[] imageCrop() {
        return this.processorOperation.crop();
    }

    /*Other*/

    public String imageByteSizeCalculate() {
        return this.processorOther.calculateBytes();
    }

    public boolean isAnAcceptedImage() {
        return this.processorOther.isAcceptable();
    }

    public boolean isAnImage() {
        return this.processorOther.isImage();
    }

    public String imageFormat() throws IOException {
        return this.processorOther.formatName();
    }

}
