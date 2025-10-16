package com.huntercodexs.image.processor.enumerator;

import lombok.Getter;

@Getter
public enum ImageType {
    BMP(true, "Bitmap"),
    GIF(true, "Graphics Interchange Format"),
    PNG(true, "Portable Network Graphics"),
    JPEG(true, "Joint Photographic Experts Group"),
    JPG(true, "Joint Photographic Experts Group"),
    TIF(false, "Tag Image File"),
    TIFF(false, "Tag Image File Format"),
    PSD(false, "Photoshop Document"),
    SVG(false, "Scalable Vector Graphics"),
    WEBP(false, "WEBP"),
    NEF(false, "Nikon Electronic Format"),
    PDF(false, "Portable Document Format");

    final boolean accepted;
    final String description;

    ImageType(boolean accepted, String description) {
        this.accepted = accepted;
        this.description = description;
    }
}
