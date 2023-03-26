package FactoryMethod;

import java.util.Arrays;

interface ImageReader {
    ImageDecoder getDecodeImage();
}

class ImageDecoder {
    private final String image;

    public ImageDecoder(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("File '%s' is decoded",image);
    }
}

class GifReader implements ImageReader {
    private final ImageDecoder decodedImage;

    public GifReader(String image) {
        this.decodedImage = new ImageDecoder(image);
    }

    @Override
    public ImageDecoder getDecodeImage() {
        return decodedImage;
    }
}

class JpegReader implements ImageReader {
    private final ImageDecoder decodedImage;

    public JpegReader(String image) {
        decodedImage = new ImageDecoder(image);
    }

    @Override
    public ImageDecoder getDecodeImage() {
        return decodedImage;
    }
}

public class FactoryMethodDemo
{
    public static void main(String[] args)
    {
        ImageReader reader = null;
        ImageDecoder decodedImage = null;
        for (final String fileName: Arrays.asList("image1.gif", "image2.jpeg"))
        {
            final String format = fileName.substring(fileName.indexOf('.') + 1);
            if (format.equals("gif")) {
                reader = new GifReader(fileName);
            }
            else if (format.equals("jpeg")) {
                reader = new JpegReader(fileName);
            }
            assert reader != null;

            decodedImage = reader.getDecodeImage();
            System.out.println(decodedImage + "with " + reader.getClass().getSimpleName());
        }
    }
}