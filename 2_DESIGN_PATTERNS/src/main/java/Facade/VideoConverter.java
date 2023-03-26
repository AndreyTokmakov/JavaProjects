package Facade;

import java.io.File;

interface Codec {
	public String getType();
}

class MPEG4CompressionCodec implements Codec {
    protected String type = "mp4";

	@Override
	public String getType() {
		return type;
	}
}

class OggCompressionCodec implements Codec {
	protected String type = "ogg";

	@Override
	public String getType() {
		return type;
	}
}

class VideoFile {
    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() {
        return codecType;
    }

    public String getName() {
        return name;
    }
}


class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4CompressionCodec();
        }
        else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}


class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println(String.format("BitrateReader: reading %s file...",codec.getType()));
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println(String.format("BitrateReader: writing %s file...",codec.getType()));
        return buffer;
    }
}


class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}


class VideoConversionFacade {
    public File convertVideo(String fileName,
    						 String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }
        
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}



public class VideoConverter {
	public static void main(String[] args) {
	     VideoConversionFacade converter = new VideoConversionFacade();
	     File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
	}
}
